package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.model.Film

interface IMovieRepository {
    fun getMovieData(): LiveData<List<Film>>
    fun getMovieDetails(movieId: Int): LiveData<Film>
}