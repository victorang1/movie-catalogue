package com.example.moviecatalogue.data.service.movie

import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieEndpoint {

    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<PopularMovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<MovieDetailResponse>
}