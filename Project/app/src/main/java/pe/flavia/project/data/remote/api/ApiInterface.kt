package pe.flavia.project.data.remote.api

import pe.flavia.project.data.remote.entities.response.MoviesResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/4/21 - 4:55 PM
    Solera Mobile
*/

interface ApiInterface {
    @GET("1?api_key=752cd23fdb3336557bf3d8724e115570&page=1")
    suspend fun movies(): Response<MoviesResponse>
}