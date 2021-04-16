package com.example.moviecatalogue.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R

/**
 * Created by victor on 16-Apr-21 11:18 AM.
 */
fun ImageView.loadImage(url: String?, errorDrawable: Int = R.drawable.ic_error_black, optionsSize: Int = 500) {
    Glide.with(this.context)
        .load(url)
        .apply(RequestOptions()
            .override(optionsSize, optionsSize)
            .error(errorDrawable)
        )
        .centerCrop()
        .into(this)
}