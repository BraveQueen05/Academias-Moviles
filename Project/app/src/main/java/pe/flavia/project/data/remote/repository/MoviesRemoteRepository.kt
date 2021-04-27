package pe.flavia.project.data.remote.repository

import pe.flavia.project.data.remote.MoviesDataSource
import pe.flavia.project.data.remote.entities.Responses
import pe.flavia.project.domain.MoviesRepository
import pe.flavia.project.domain.model.Movies

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:38 AM
    Solera Mobile
*/

class MoviesRemoteRepository(private val moviesDataSource: MoviesDataSource): MoviesRepository {
    override suspend fun getMovies(): Responses<Movies> = this.moviesDataSource.getMovies()
}