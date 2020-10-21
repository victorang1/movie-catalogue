package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.common.NetworkBoundResource
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.LocalFilmSource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.data.service.movie.MovieService
import com.example.moviecatalogue.utils.ResponseHelper

class MovieRepository(
    private val movieService: MovieService,
    private val localFilmSource: LocalFilmSource
) : IMovieRepository {

    override fun getMovieData(): LiveData<Resource<PagedList<Film>>> {
        return object : NetworkBoundResource<PagedList<Film>, PopularMovieResponse>() {
            override fun loadFromDB(): LiveData<PagedList<Film>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localFilmSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<Film>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularMovieResponse>> =
                movieService.getPopularMovies()

            override fun saveCallResult(data: PopularMovieResponse) {
                val movies = ResponseHelper.convertToFilm(data)
                localFilmSource.insertFilm(movies)
            }
        }.toLiveData()
    }

    override fun getMovieDetails(movieId: Int): LiveData<Resource<Film>> {
        return object : NetworkBoundResource<Film, MovieDetailResponse>() {
            override fun loadFromDB(): LiveData<Film> =
                localFilmSource.getMovieById(movieId)

            override fun shouldFetch(data: Film?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<MovieDetailResponse>> =
                movieService.getMovieDetails(movieId)

            override fun saveCallResult(data: MovieDetailResponse) {
                val movie = ResponseHelper.convertToFilm(data)
                localFilmSource.insertFilm(movie)
            }
        }.toLiveData()
    }
}