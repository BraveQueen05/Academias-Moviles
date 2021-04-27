package pe.flavia.project.data.remote.connections

import pe.flavia.project.data.remote.MoviesDataSource
import pe.flavia.project.data.remote.api.ApiInterface
import pe.flavia.project.data.remote.api.ApiModule
import pe.flavia.project.data.remote.entities.Responses
import pe.flavia.project.domain.model.Movies

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/14/21 - 9:21 PM
    Solera Mobile
*/

class MoviesNetwork: MoviesDataSource {
    override suspend fun getMovies(): Responses<Movies> {
        val appApi: ApiInterface = ApiModule().build()
        return try {
            val response = appApi.movies()
            if (response.isSuccessful) {
                val body = response.body()
                Responses.Complete(body?.toMovies() ?: Movies())
            } else {
                Responses.Failure(Exception(response.errorBody()?.toString()))
            }
        } catch (e: Exception) {
            Responses.Failure(e)
        }
    }
}