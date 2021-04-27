package pe.flavia.project.data.remote.entities.response

import pe.flavia.project.BuildConfig
import pe.flavia.project.domain.model.Results

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/14/21 - 1:41 PM
    Solera Mobile
*/

class ResultsResponse(
        val adult               : Boolean       ? = null,
        val backdrop_path       : String        ? = null,
        val genre_ids           : List<Int>     ? = null,
        val id                  : Int           ? = null,
        val media_type          : String        ? = null,
        val original_language   : String        ? = null,
        val original_title      : String        ? = null,
        val overview            : String        ? = null,
        val popularity          : Double        ? = null,
        val poster_path         : String        ? = null,
        val release_date        : String        ? = null,
        val title               : String        ? = null,
        val video               : Boolean       ? = null,
        val vote_average        : Double        ? = null,
        val vote_count          : Int           ? = null
)

fun List<ResultsResponse>?.toResultsList(): List<Results> = this?.map {
    Results(
            it.adult ?: false,
            "${BuildConfig.IMG_URL}${it.backdrop_path}",
            it.genre_ids ?: emptyList(),
            it.id ?: -1,
            it.media_type ?: "",
            it.original_language ?: "",
            it.original_title ?: "",
            it.overview ?: "",
            it.popularity ?: 0.0,
            "${BuildConfig.IMG_URL}${it.poster_path}",
            it.release_date ?: "",
            it.title ?: "",
            it.video ?: false,
            it.vote_average ?: 0.0,
            it.vote_count ?: -1
    )
} ?: emptyList()