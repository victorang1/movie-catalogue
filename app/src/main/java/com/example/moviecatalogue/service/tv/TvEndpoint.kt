package com.example.moviecatalogue.service.tv

import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvEndpoint {

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") apiKey: String): Call<PopularTvResponse>

    @GET("tv/{tv_id}")
    fun getTvDetails(
        @Query("api_key") apiKey: String,
        @Path("tv_id") tvId: Int
    ): Call<MovieDetailResponse>
}