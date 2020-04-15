package plus.yeti.plantain

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

expect fun platformName(): String

fun createApplicationScreenMessage(): String {
    return "Kotlin Rocks on ${platformName()}"
}

suspend fun getCommonResponse(): String? {
    val client = HttpClient()
    val url = "https://hiring.revolut.codes/api/android/latest?base=EUR"
    return withContext(Dispatchers.Default) { client.get<String>(url) }
}