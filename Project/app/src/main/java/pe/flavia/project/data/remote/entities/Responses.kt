package pe.flavia.project.data.remote.entities

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:10 AM
    Solera Mobile
*/

sealed class Responses<out T> {
    data class Success<T>(val data: List<T>?) : Responses<T>()
    data class Complete<T>(val data: T?) : Responses<T>()
    data class Failure(val exception: Exception?) : Responses<Nothing>()
}