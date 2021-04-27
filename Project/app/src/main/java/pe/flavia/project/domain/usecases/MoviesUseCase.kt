package pe.flavia.project.domain.usecases

import pe.flavia.project.domain.MoviesRepository

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:27 AM
    Solera Mobile
*/

class MoviesUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke() = moviesRepository.getMovies()
}