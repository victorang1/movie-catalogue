package com.example.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.IMovieRepository

class MovieViewModel(private val movieRepository: IMovieRepository) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun getMovieData(): LiveData<Resource<PagedList<Film>>> = movieRepository.getMovieData()

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading
}