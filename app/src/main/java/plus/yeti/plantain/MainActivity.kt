package plus.yeti.plantain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commonCode()
    }

    private fun commonCode() {
        lifecycleScope.launch {
            val sharedResult = withContext(Dispatchers.IO) { getCommonResponse() }
            labelTextView.text = sharedResult ?: "no data"
        }
    }
}
