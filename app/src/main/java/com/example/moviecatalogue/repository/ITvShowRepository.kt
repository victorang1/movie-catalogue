package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.local.entity.Film

interface ITvShowRepository {
    fun getTvShowData(): LiveData<List<Film>>
    fun getTvDetails(movieId: Int): LiveData<Film>
}