package pe.flavia.storage.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/24/21 - 5:10 PM
    Solera Mobile
*/

@Parcelize
class Book(val id: Int? = null, val name: String, val author: String) : Parcelable