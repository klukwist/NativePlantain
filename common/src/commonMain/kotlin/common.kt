package plus.yeti.plantain

import io.ktor.client.engine.HttpClientEngine

expect fun platformName(): String

fun createApplicationScreenMessage() : String {
    return "Kotlin Rocks on ${platformName()}"
}

fun api(){
//    val api = HttpClientEngine
}