package com.example.moviecatalogue.data.service.movie

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse

interface MovieService {

    fun getPopularMovies() : LiveData<ApiResponse<PopularMovieResponse>>

    fun getMovieDetails(movieId: Int) : LiveData<ApiResponse<MovieDetailResponse>>
}