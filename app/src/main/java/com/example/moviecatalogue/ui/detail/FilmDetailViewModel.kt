package com.example.moviecatalogue.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.IFavoriteRepository
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.utils.ResponseHelper
import com.google.gson.Gson
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FilmDetailViewModel(
    private val movieRepository: IMovieRepository,
    private val tvShowRepository: ITvShowRepository,
    private val favoriteRepository: IFavoriteRepository
) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()
    private val isFavorite = MutableLiveData<Resource<Boolean>>()

    fun getFavoriteStatus(): LiveData<Resource<Boolean>> = isFavorite

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading

    fun loadDataById(id: Int, type: String): LiveData<Resource<Film>> {
        return when (type) {
            AppConstant.MOVIE -> movieRepository.getMovieDetails(id)
            else -> tvShowRepository.getTvDetails(id)
        }
    }

    fun isFavoriteFilm(id: Int, type: String): LiveData<Resource<Boolean>> {
        isLoading.value = true
        return when (type) {
            AppConstant.MOVIE -> favoriteRepository.isFavoriteMovie(id)
            else -> favoriteRepository.isFavoriteTvShow(id)
        }
    }

    fun addToFavorite(film: Film): LiveData<Resource<Boolean>> =
        favoriteRepository.insertFavorite(ResponseHelper.convertToFavorite(film))

}