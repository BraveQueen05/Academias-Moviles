package pe.flavia.project.data.remote

import pe.flavia.project.data.remote.entities.Responses
import pe.flavia.project.domain.model.Movies

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:43 AM
    Solera Mobile
*/

interface MoviesDataSource {
    suspend fun getMovies(): Responses<Movies>
}