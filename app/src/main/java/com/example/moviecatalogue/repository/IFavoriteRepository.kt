package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite

interface IFavoriteRepository {
    fun getAllFavoriteMovies(): LiveData<Resource<PagedList<Favorite>>>
    fun getAllFavoriteTvs(): LiveData<Resource<PagedList<Favorite>>>
    fun searchMovies(title: String): LiveData<Resource<PagedList<Favorite>>>
    fun searchTvShows(title: String): LiveData<Resource<PagedList<Favorite>>>
    fun getFavoriteMovieById(filmId: Int): LiveData<Resource<Favorite>>
    fun getFavoriteTvShowById(filmId: Int): LiveData<Resource<Favorite>>
    fun insertFavorite(favorite: Favorite): LiveData<Resource<Boolean>>
    fun deleteFromFavorite(favorite: Favorite): LiveData<Resource<Boolean>>
    fun isFavoriteMovie(movieId: Int): LiveData<Resource<Boolean>>
    fun isFavoriteTvShow(tvId: Int): LiveData<Resource<Boolean>>
}