package com.example.moviecatalogue.data.local

import androidx.paging.DataSource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.data.local.room.FavoriteDao

class LocalFavoriteSource(private val favoriteDao: FavoriteDao) {

    fun getAllMovies(): DataSource.Factory<Int, Favorite> =
        favoriteDao.getAllFavoritesByType(AppConstant.MOVIE)

    fun getAllTvShows(): DataSource.Factory<Int, Favorite> =
        favoriteDao.getAllFavoritesByType(AppConstant.TV_SHOW)

    fun getFilteredMovies(title: String): DataSource.Factory<Int, Favorite> =
        favoriteDao.filterFilms(title, AppConstant.MOVIE)

    fun getFilteredTvShow(title: String): DataSource.Factory<Int, Favorite> =
        favoriteDao.filterFilms(title, AppConstant.TV_SHOW)

    suspend fun insertFilm(favoriteFilm: Favorite) = favoriteDao.insertFavorite(favoriteFilm)

    suspend fun deleteFromFavorite(favoriteFilm: Favorite) = favoriteDao.deleteFromFavorite(favoriteFilm)

    suspend fun getFavoriteMovieById(movieId: Int): Favorite =
        favoriteDao.getFavoriteFilm(movieId, AppConstant.MOVIE)

    suspend fun getFavoriteTvShowById(tvId: Int): Favorite =
        favoriteDao.getFavoriteFilm(tvId, AppConstant.TV_SHOW)
}