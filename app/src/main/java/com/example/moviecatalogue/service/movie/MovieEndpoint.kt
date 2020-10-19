package com.example.moviecatalogue.service.movie

import com.example.moviecatalogue.service.datamodel.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndpoint {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopularMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Query("api_key") apiKey: String,
        @Path("movie_id") movieId: Int
    ): Call<MovieDetailResponse>
}