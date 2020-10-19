package com.example.moviecatalogue.service.tv

import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import retrofit2.Call
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {

    fun getPopularTv(
        @Query("api_key") apiKey: String,
        apiHandler: ApiHandler<PopularTvResponse>
    )

    fun getTvDetails(
        @Query("api_key") apiKey: String,
        @Path("tv_id") tvId: Int,
        apiHandler: ApiHandler<PopularTvResponse>
    )
}