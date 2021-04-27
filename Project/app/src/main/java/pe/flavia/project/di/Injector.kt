package pe.flavia.project.di

import pe.flavia.project.data.remote.api.ApiModule
import pe.flavia.project.data.remote.connections.MoviesNetwork
import pe.flavia.project.data.remote.repository.MoviesRemoteRepository

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/4/21 - 4:35 PM
    Solera Mobile
*/

object Injector {
    private val apiService by lazy { ApiModule().build() }
    private val moviesNetwork by lazy {
        MoviesRemoteRepository(MoviesNetwork())
    }

    fun provideAuthorizationRepository() = moviesNetwork
}