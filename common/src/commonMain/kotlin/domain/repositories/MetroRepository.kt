package plus.yeti.plantain.domain.repositories

import plus.yeti.plantain.data.entities.Metro

interface MetroRepository {
    fun getMetro(region: String): Metro?
}