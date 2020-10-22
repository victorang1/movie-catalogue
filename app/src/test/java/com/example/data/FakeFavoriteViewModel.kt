package com.example.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.IFavoriteRepository

class FakeFavoriteViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    private val selectedFilmType = MutableLiveData<String>()

    fun setSelectedFilmType(filmType: String) {
        selectedFilmType.value = filmType
    }

    fun getShowedData(): LiveData<Resource<PagedList<Favorite>>> =
        Transformations.switchMap(selectedFilmType) { filmType ->
            when (filmType) {
                "Movie" -> favoriteRepository.getAllFavoriteMovies()
                else -> favoriteRepository.getAllFavoriteTvs()
            }
        }

    fun searchUser(title: String): LiveData<Resource<PagedList<Favorite>>> =
        when (selectedFilmType.value) {
            "Movie" -> favoriteRepository.searchMovies(title)
            else -> favoriteRepository.searchTvShows(title)
        }

    fun removeFromFavorite(favorite: Favorite): LiveData<Resource<Boolean>> =
        favoriteRepository.deleteFromFavorite(favorite)
}