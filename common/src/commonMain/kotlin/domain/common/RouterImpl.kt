package plus.yeti.plantain.domain.common

import plus.yeti.plantain.data.entities.Metro
import plus.yeti.plantain.data.entities.Station
import plus.yeti.plantain.domain.repositories.Router

class RouterImpl(private val metro: Metro) : Router {

    override fun getRoute(fromStationId: String, toStationId: String): List<Station> {
        TODO("Not yet implemented")
    }

}