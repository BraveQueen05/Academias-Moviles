package pe.flavia.project.presentation.components

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 1:30 AM
    Solera Mobile
*/

object ComponentsAdapters {
    @JvmStatic
    @BindingAdapter("setImageFromUrl")
    fun setImageFromUrl(imageView: AppCompatImageView, url: String?) {
        url?.let {
            Glide.with(imageView.context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
        }
    }
}