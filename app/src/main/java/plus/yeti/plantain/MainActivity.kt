package plus.yeti.plantain

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        commonCode()
    }

    private fun commonCode() {
        val sharedResult = createApplicationScreenMessage()
    }
}
