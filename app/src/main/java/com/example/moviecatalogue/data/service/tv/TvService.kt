package com.example.moviecatalogue.data.service.tv

import com.example.moviecatalogue.data.service.ApiHandler
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse

interface TvService {

    fun getPopularTv(
        apiKey: String,
        apiHandler: ApiHandler<PopularTvResponse>
    )

    fun getTvDetails(
        apiKey: String, tvId: Int, apiHandler: ApiHandler<TvDetailResponse>
    )
}