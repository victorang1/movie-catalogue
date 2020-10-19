package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.Film

interface ITvShowRepository {
    fun getTvShowData(): LiveData<List<Film>>
}