package pe.flavia.optionaltwo

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.NestedScrollView
import java.text.NumberFormat
import java.util.*

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/28/21 - 1:43 AM
    Solera Mobile
*/

val Double.intoCurrency: String
    get(){
        val format = NumberFormat.getCurrencyInstance(Locale("es", "PE"))
        format.minimumFractionDigits = 2
        format.maximumFractionDigits = 2
        return format.format(this)
    }

fun Context?.getDrawableByResourceName(name: String?): Drawable? {
    return this?.let {
        val identifier = it.resources.getIdentifier(name, "drawable", this.packageName)
        return AppCompatResources.getDrawable(this, identifier)
    }
}