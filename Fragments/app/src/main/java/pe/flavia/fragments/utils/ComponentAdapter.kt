package pe.flavia.fragments.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/8/21 - 12:36 AM
    Solera Mobile
*/

object ComponentAdapter {
    @JvmStatic
    @BindingAdapter("setImageByResourceName")
    fun setImageByResourceName(view: ImageView, idName: String?) {
        view.setImageDrawable(view.context.getDrawableByResourceName(idName ?: String()) ?: return)
    }
}