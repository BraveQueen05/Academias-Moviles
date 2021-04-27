package pe.flavia.project.data.remote.entities.response

import pe.flavia.project.BuildConfig
import pe.flavia.project.domain.model.Author
import pe.flavia.project.domain.model.Movies

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/4/21 - 5:38 PM
    Solera Mobile
*/

class MoviesResponse(
        val average_rating  : Double                ?= null,
        val backdrop_path   : String                ?= null,
        val created_by      : AuthorResponse        ?= null,
        val description     : String                ?= null,
        val id              : Int                   ?= null,
        val iso_3166_1      : String                ?= null,
        val iso_639_1       : String                ?= null,
        val name            : String                ?= null,
        val page            : Int                   ?= null,
        val poster_path     : String                ?= null,
        val public          : Boolean               ?= null,
        val results         : List<ResultsResponse> ?= null,
        val revenue         : Long                  ?= null,
        val runtime         : Int                   ?= null,
        val sort_by         : String                ?= null,
        val total_pages     : Int                   ?= null,
        val total_results   : Int                   ?= null,
){
    fun toMovies(): Movies = Movies(
            this.average_rating?.toFloat() ?: 0f,
            "${BuildConfig.IMG_URL}${this.backdrop_path}",
            this.created_by?.toAuthor() ?: Author(),
            this.description ?: "",
            this.id ?: -1,
            this.iso_3166_1 ?: "",
            this.iso_639_1 ?: "",
            this.name ?: "",
            this.page ?: -1,
            "${BuildConfig.IMG_URL}${this.poster_path}",
            this.public ?: false,
            this.results?.toResultsList() ?: emptyList(),
            this.revenue ?: -1L,
            this.runtime ?: -1,
            this.sort_by ?: "",
            this.total_pages ?: -1,
            this.total_results ?: -1
    )
}