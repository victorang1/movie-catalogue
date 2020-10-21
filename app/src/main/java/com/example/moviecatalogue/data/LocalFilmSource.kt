package com.example.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.constant.FilmType
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.local.room.FilmDao

class LocalFilmSource(private val filmDao: FilmDao) {

    fun getAllMovies(): DataSource.Factory<Int, Film> =
        filmDao.getAllFilmsByType(FilmType.MOVIE)

    fun getAllTvShows(): DataSource.Factory<Int, Film> =
        filmDao.getAllFilmsByType(FilmType.TV_SHOW)

    fun insertFilms(films: List<Film>) = filmDao.insertFilm(films)

    fun insertFilm(film: Film) = filmDao.insertFilm(film)

    fun getMovieById(id: Int): LiveData<Film> =
        filmDao.getFilmById(id, FilmType.MOVIE)

    fun getTvShowById(id: Int): LiveData<Film> =
        filmDao.getFilmById(id, FilmType.TV_SHOW)
}