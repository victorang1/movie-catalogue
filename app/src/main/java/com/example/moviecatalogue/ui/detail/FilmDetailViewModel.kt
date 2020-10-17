package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository

class FilmDetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvShowRepository
) : ViewModel() {

    fun loadDataById(position: Int, type: Int): Film {
        return if (type == R.string.text_type_movie) movieRepository.getMovieData()[position]
        else tvShowRepository.getTvShowData()[position]
    }
}