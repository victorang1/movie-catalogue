package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.IMovieRepository

class MovieViewModel(val movieRepository: IMovieRepository) : ViewModel() {

    fun getMovieData(): LiveData<ArrayList<Film>> {
        val movies = MutableLiveData<ArrayList<Film>>()
        movies.value = movieRepository.getMovieData()
        return movies
    }
}