package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.data.service.ApiHandler
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.data.service.movie.MovieService
import com.example.moviecatalogue.utils.EspressoIdlingResource
import com.example.moviecatalogue.utils.ResponseHelper

class MovieRepository(private val movieService: MovieService) : IMovieRepository {

    override fun getMovieData(): LiveData<List<Film>> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<List<Film>>()
        movieService.getPopularMovies(API_KEY, object : ApiHandler<PopularMovieResponse> {

            override fun onSuccess(response: PopularMovieResponse) {
                movies.value = ResponseHelper.convertToFilm(response)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(throwable: Throwable) {
                EspressoIdlingResource.decrement()
                throw throwable
            }
        })
        return movies
    }

    override fun getMovieDetails(movieId: Int): LiveData<Film> {
        EspressoIdlingResource.increment()
        val movies = MutableLiveData<Film>()
        movieService.getMovieDetails(API_KEY, movieId, object : ApiHandler<MovieDetailResponse> {

            override fun onSuccess(response: MovieDetailResponse) {
                movies.value = ResponseHelper.convertToFilm(response)
                EspressoIdlingResource.decrement()
            }

            override fun onFailure(throwable: Throwable) {
                EspressoIdlingResource.decrement()
                throw throwable
            }
        })
        return movies
    }
}