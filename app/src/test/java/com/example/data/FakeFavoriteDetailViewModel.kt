package com.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.IFavoriteRepository

class FakeFavoriteDetailViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    fun loadDataById(id: Int, type: String): LiveData<Resource<Favorite>> {
        return when (type) {
            "Movie" -> favoriteRepository.getFavoriteMovieById(id)
            else -> favoriteRepository.getFavoriteTvShowById(id)
        }
    }
}