package com.example.moviecatalogue.service.tv

import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.service.datamodel.tv.TvDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvEndpoint {

    @GET("tv/popular")
    fun getPopularTv(@Query("api_key") apiKey: String): Call<PopularTvResponse>

    @GET("tv/{tv_id}")
    fun getTvDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String
    ): Call<TvDetailResponse>
}