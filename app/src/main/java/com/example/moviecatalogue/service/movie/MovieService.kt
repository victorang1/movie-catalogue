package com.example.moviecatalogue.service.movie

import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.movie.PopularMovieResponse

interface MovieService {

    fun getPopularMovies(apiKey: String, apiHandler: ApiHandler<PopularMovieResponse>)

    fun getMovieDetails(apiKey: String, movieId: Int, apiHandler: ApiHandler<MovieDetailResponse>)
}