package com.example.moviecatalogue.constant

import android.content.res.Resources
import com.example.moviecatalogue.R
import org.koin.core.KoinComponent
import org.koin.core.inject

object FilmType : KoinComponent {

    private val resources: Resources by inject()
    var MOVIE = resources.getString(R.string.text_type_movie)
    var TV_SHOW = resources.getString(R.string.text_type_tv_show)
}