package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.local.room.FilmDao

class LocalFilmSource(private val filmDao: FilmDao) {

    fun getAllMovies(): DataSource.Factory<Int, Film> =
        filmDao.getAllFilmsByType(AppConstant.MOVIE)

    fun getAllTvShows(): DataSource.Factory<Int, Film> =
        filmDao.getAllFilmsByType(AppConstant.TV_SHOW)

    fun insertFilm(films: List<Film>) = filmDao.insertFilm(films)

    fun insertFilm(film: Film) = filmDao.insertFilm(film)

    fun getMovieById(id: Int): LiveData<Film> =
        filmDao.getFilmById(id, AppConstant.MOVIE)

    fun getTvShowById(id: Int): LiveData<Film> =
        filmDao.getFilmById(id, AppConstant.TV_SHOW)
}