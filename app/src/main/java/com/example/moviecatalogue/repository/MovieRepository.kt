package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.service.movie.MovieService
import com.example.moviecatalogue.utils.ResponseHelper

class MovieRepository(private val movieService: MovieService) : IMovieRepository {

    override fun getMovieData(): LiveData<List<Film>> {
        val movies = MutableLiveData<List<Film>>()
        movieService.getPopularMovies(API_KEY, object : ApiHandler<PopularMovieResponse> {

            override fun onSuccess(response: PopularMovieResponse) {
                movies.value = ResponseHelper.convertToFilm(response)
            }

            override fun onFailure(throwable: Throwable) {
                throw throwable
            }
        })
        return movies
    }

    override fun getMovieDetails(movieId: Int): LiveData<Film> {
        val movies = MutableLiveData<Film>()
        movieService.getMovieDetails(API_KEY, movieId, object : ApiHandler<MovieDetailResponse> {

            override fun onSuccess(response: MovieDetailResponse) {
                movies.value = ResponseHelper.convertToFilm(response)
            }

            override fun onFailure(throwable: Throwable) {
                throw throwable
            }
        })
        return movies
    }
}