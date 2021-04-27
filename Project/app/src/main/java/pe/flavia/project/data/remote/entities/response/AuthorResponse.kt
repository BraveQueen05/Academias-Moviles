package pe.flavia.project.data.remote.entities.response

import pe.flavia.project.domain.model.Author

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/4/21 - 5:40 PM
    Solera Mobile
*/

class AuthorResponse(
    val gravatar_hash   : String ?= null,
    val id              : String ?= null,
    val name            : String ?= null,
    val username        : String ?= null
){
    fun toAuthor(): Author = Author(this.gravatar_hash ?: "", this.id ?: "", this.name ?: "", this.username ?: "")
}