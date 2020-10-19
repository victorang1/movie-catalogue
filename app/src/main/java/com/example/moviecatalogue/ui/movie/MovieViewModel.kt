package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.IMovieRepository

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun getMovieData(): LiveData<List<Film>> = movieRepository.getMovieData()


    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading
}