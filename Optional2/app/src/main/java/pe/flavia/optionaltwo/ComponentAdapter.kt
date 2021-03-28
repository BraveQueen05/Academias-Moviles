package pe.flavia.optionaltwo

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/28/21 - 2:32 AM
    Solera Mobile
*/

object ComponentAdapter {
    @JvmStatic
    @BindingAdapter("setImageByResourceName")
    fun setImageByResourceName(view: ImageView, idName: String?) {
        view.setImageDrawable(view.context.getDrawableByResourceName(idName ?: String()) ?: return)
    }
}