package developer.abdulahad.homework25.DB

import developer.abdulahad.homework25.Models.Traffics

interface MyDbInterFace {
//    Traffics
    fun addTraffics(traffics: Traffics)
    fun getTraffics() : ArrayList<Traffics>
    fun updateTraffics(traffics: Traffics) : Int
    fun deleteTraffics(traffics: Traffics)
}