package com.example.moviecatalogue.data.service.movie

import com.example.moviecatalogue.data.service.ApiHandler
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse

interface MovieService {

    fun getPopularMovies(apiKey: String, apiHandler: ApiHandler<PopularMovieResponse>)

    fun getMovieDetails(apiKey: String, movieId: Int, apiHandler: ApiHandler<MovieDetailResponse>)
}