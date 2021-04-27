package pe.flavia.project.presentation.utils

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import pe.flavia.project.R

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 10:59 PM
    Solera Mobile
*/

val View.visible: Unit
    get() {
        this.visibility = View.VISIBLE
    }

val View.gone: Unit
    get() {
        this.visibility = View.GONE
    }

fun AppCompatImageView.setImgByURLAndLoading(url: String, onResourceReady: () -> Unit){
    Glide.with(this.context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                Toast.makeText(this@setImgByURLAndLoading.context, this@setImgByURLAndLoading.context.getString(R.string.error_loading_img), Toast.LENGTH_SHORT).show()
                return false
            }

            override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                onResourceReady()
                return false
            }

        })
        .into(this)
}