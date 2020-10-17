package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.IMovieRepository

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    fun getMovieData(): ArrayList<Film> = movieRepository.getMovieData()
}