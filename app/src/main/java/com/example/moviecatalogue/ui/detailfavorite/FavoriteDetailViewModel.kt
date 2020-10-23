package com.example.moviecatalogue.ui.detailfavorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.IFavoriteRepository

class FavoriteDetailViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    private val isLoading = MutableLiveData<Boolean>()

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): LiveData<Boolean> = isLoading

    fun loadDataById(id: Int, type: String): LiveData<Resource<Favorite>> {
        return when (type) {
            AppConstant.MOVIE -> favoriteRepository.getFavoriteMovieById(id)
            else -> favoriteRepository.getFavoriteTvShowById(id)
        }
    }
}