package pe.flavia.project.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.flavia.project.data.remote.entities.Responses
import pe.flavia.project.domain.model.Movies
import pe.flavia.project.domain.usecases.MoviesUseCase

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 12:52 AM
    Solera Mobile
*/

class MoviesViewModel(private val moviesUseCase: MoviesUseCase): ViewModel() {
    private val mMovies = MutableLiveData<Movies>()
    val movies: LiveData<Movies> = mMovies

    private val mError = MutableLiveData<String>()
    val error:LiveData<String> = mError

    fun getMovies(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO){
                moviesUseCase()
            }
            when(result){
                is Responses.Complete ->{
                    val user = result.data
                    user?.let {
                        mMovies.value = it
                    }
                }
                is Responses.Failure ->
                    mError.value = result.exception?.message
            }
        }
    }
}