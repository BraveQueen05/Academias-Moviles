package pe.flavia.project.domain

import pe.flavia.project.data.remote.entities.Responses
import pe.flavia.project.domain.model.Movies

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/14/21 - 9:37 PM
    Solera Mobile
*/

interface MoviesRepository {
    suspend fun getMovies(): Responses<Movies>
}