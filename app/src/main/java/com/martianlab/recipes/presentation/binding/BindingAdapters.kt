package com.martianlab.recipes.presentation.binding

import android.view.View
import android.widget.*
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.martianlab.recipes.R



/**
 * Data Binding adapters specific to the app.
 */
object BindingAdapters {
    @JvmStatic
    @BindingAdapter("visibleGone")
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }


    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(view: View, url : String?){
        Glide
                .with(view.context)
                .load(url)
                //.placeholder(R.drawable.ic_photo_black_24dp)
                //.fallback(R.drawable.ic_photo_black_24dp)
                .into(view as ImageView)
    }

    @JvmStatic
    @BindingAdapter("onNavigationClick")
    fun onNavigationClick(view: Toolbar, onClick : ()->Unit) {
        view.setNavigationOnClickListener { onClick() }
    }

}