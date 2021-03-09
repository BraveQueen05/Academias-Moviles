package pe.flavia.fragments.entities

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/7/21 - 9:16 PM
    Solera Mobile
*/

@Parcelize
class User(val id: Int = -1, val img: String = "", val name: String = "", val phone: String = "", val email: String ="", val web: String ="") : Parcelable{
    @IgnoredOnParcel
    var isSelected = false
}

enum class EUser(val id: Int, val img: String, val uName: String, val phone: String, val email: String, val web: String){
    USER1(1, "img_user", "Flavia Figueroa", "933453996", "ffigueroa052000@gmail.pe", "www.flavialand.com"),
    USER2(2, "img_user", "Daniela Figueroa", "111111111", "dfigueroa@gmail.pe", "www.danielaland.com"),
    USER3(3, "img_user", "Stuardo Figueroa", "222222222", "sfigueroa@gmail.pe", "www.stuardoland.com"),
    USER4(4, "img_user", "Norka Castillo", "333333333", "ncastillo@gmail.pe", "www.norkaland.com"),
    USER5(5, "img_user", "Kira Figueroa", "444444444", "kfigueroa@gmail.pe", "www.kiraland.com");

    companion object{
        fun getUsersList(): MutableList<User> {
            val newList = values().map { User(it.id, it.img, it.uName, it.phone, it.email, it.web) }.toMutableList()
            newList.forEach {
                it.isSelected = newList.indexOf(it) == 0
            }
            return newList
        }
    }
}