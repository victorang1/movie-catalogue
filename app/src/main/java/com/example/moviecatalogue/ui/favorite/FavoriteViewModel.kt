package com.example.moviecatalogue.ui.favorite

import androidx.lifecycle.*
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.IFavoriteRepository

class FavoriteViewModel(private val favoriteRepository: IFavoriteRepository) : ViewModel() {

    private val selectedFilmType = MutableLiveData<String>()
    private val isLoading = MutableLiveData<Boolean>()

    fun setSelectedFilmType(filmType: String) {
        selectedFilmType.value = filmType
    }

    fun setLoading(loading: Boolean) {
        isLoading.value = loading
    }

    fun getLoadingStatus(): MutableLiveData<Boolean> = isLoading

    fun getShowedData(): LiveData<Resource<PagedList<Favorite>>> =
        Transformations.switchMap(selectedFilmType) { filmType ->
            when (filmType) {
                AppConstant.MOVIE -> favoriteRepository.getAllFavoriteMovies()
                else -> favoriteRepository.getAllFavoriteTvs()
            }
        }

    fun searchUser(title: String): LiveData<Resource<PagedList<Favorite>>> =
        when (selectedFilmType.value) {
            AppConstant.MOVIE -> favoriteRepository.searchMovies(title)
            else -> favoriteRepository.searchTvShows(title)
        }
}