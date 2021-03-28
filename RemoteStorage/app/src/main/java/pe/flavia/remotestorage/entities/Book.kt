package pe.flavia.remotestorage.entities

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/24/21 - 5:10 PM
    Solera Mobile
*/

@Keep
@Parcelize
class Book(val idBook: String? = "", val bookName: String ?= "", val bookAuthor: String ?= "") : Parcelable{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "idBook" to idBook,
            "bookName" to bookName,
            "bookAuthor" to bookAuthor
        )
    }
}