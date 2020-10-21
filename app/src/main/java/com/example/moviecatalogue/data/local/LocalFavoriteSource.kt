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

    fun insertFilm(favoriteFilm: Favorite) = favoriteDao.insertFavorite(favoriteFilm)

    fun deleteFromFavorite(favoriteFilm: Favorite) = favoriteDao.deleteFromFavorite(favoriteFilm)
}