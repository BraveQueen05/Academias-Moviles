package pe.flavia.project.presentation.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pe.flavia.project.domain.usecases.MoviesUseCase

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:56 AM
    Solera Mobile
*/

class MoviesViewModelFactory(private val moviesUseCase: MoviesUseCase): ViewModelProvider.Factory  {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesUseCase) as T
    }
}