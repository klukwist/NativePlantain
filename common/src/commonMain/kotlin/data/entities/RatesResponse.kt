package plus.yeti.plantain.data.entities

//import kotlinx.serialization.Serializable

//@Serializable
data class RatesResponse(
    val baseCurrency: String,
    val rates: Map<String, Float> = emptyMap()
)