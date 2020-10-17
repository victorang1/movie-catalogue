package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.repository.IMovieRepository

class MovieViewModel(val movieRepository: IMovieRepository) : ViewModel() {

    fun getMovieData(): LiveData<ArrayList<Movie>> {
        val movies = MutableLiveData<ArrayList<Movie>>()
        movies.value = movieRepository.getMovieData()
        return movies
    }
}