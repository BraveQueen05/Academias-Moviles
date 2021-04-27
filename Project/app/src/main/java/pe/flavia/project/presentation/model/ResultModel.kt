package pe.flavia.project.presentation.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import pe.flavia.project.BuildConfig
import pe.flavia.project.domain.model.Results

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 3:00 AM
    Solera Mobile
*/

@Parcelize
class ResultModel(
    val adult               : Boolean       = false,
    val backdrop_path       : String        = "",
    val genre_ids           : List<Int>     = emptyList(),
    val id                  : Int           = -1,
    val media_type          : String        = "",
    val original_language   : String        = "",
    val original_title      : String        = "",
    val overview            : String        = "",
    val popularity          : Double        = 0.0,
    val poster_path         : String        = "",
    val release_date        : String        = "",
    val title               : String        = "",
    val video               : Boolean       = false,
    val vote_average        : Double        = 0.0,
    val vote_count          : Int           = -1,
): Parcelable {
    companion object{
        fun toResultModel(result: Results): ResultModel = ResultModel(
            result.adult,
            result.backdrop_path,
            result.genre_ids,
            result.id,
            result.media_type,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count
        )

        fun toResult(result: ResultModel): Results = Results(
            result.adult,
            result.backdrop_path,
            result.genre_ids,
            result.id,
            result.media_type,
            result.original_language,
            result.original_title,
            result.overview,
            result.popularity,
            result.poster_path,
            result.release_date,
            result.title,
            result.video,
            result.vote_average,
            result.vote_count
        )
    }
}