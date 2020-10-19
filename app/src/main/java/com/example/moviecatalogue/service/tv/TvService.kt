package com.example.moviecatalogue.service.tv

import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse

interface TvService {

    fun getPopularTv(
        apiKey: String,
        apiHandler: ApiHandler<PopularTvResponse>
    )

    fun getTvDetails(
        apiKey: String, tvId: Int, apiHandler: ApiHandler<PopularTvResponse>
    )
}