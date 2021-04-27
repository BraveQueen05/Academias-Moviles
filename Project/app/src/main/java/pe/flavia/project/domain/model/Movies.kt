package pe.flavia.project.domain.model

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/20/21 - 12:58 AM
    Solera Mobile
*/

class Movies(
    val average_rating  : Float                 = 0f,
    val backdrop_path   : String                = "",
    val created_by      : Author                = Author(),
    val description     : String                = "",
    val id              : Int                   = -1,
    val iso_3166_1      : String                = "",
    val iso_639_1       : String                = "",
    val name            : String                = "",
    val page            : Int                   = -1,
    val poster_path     : String                = "",
    val public          : Boolean               = false,
    val results         : List<Results>         = emptyList(),
    val revenue         : Long                  = -1L,
    val runtime         : Int                   = -1,
    val sort_by         : String                = "",
    val total_pages     : Int                   = -1,
    val total_results   : Int                   = -1,
)