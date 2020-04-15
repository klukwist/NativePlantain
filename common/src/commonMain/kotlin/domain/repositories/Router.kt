package plus.yeti.plantain.domain.repositories

import plus.yeti.plantain.data.entities.Station

interface Router {
    fun getRoute(fromStationId: String, toStationId: String): List<Station>
}