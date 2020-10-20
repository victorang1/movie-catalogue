package com.example.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository

class FilmDetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvShowRepository
) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading

    fun loadDataById(id: Int, type: Int): LiveData<Film> {
        isLoading.value = true
        return when (type) {
            R.string.text_type_movie -> movieRepository.getMovieDetails(id)
            else -> tvShowRepository.getTvDetails(id)
        }
    }

}