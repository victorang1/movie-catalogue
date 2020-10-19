package com.example.moviecatalogue.service.movie

import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.PopularMovieResponse

interface MovieService {

    fun getPopularMovies(apiKey: String, apiHandler: ApiHandler<PopularMovieResponse>)

    fun getMovieDetails(apiKey: String, movieId: Int, apiHandler: ApiHandler<MovieDetailResponse>)
}