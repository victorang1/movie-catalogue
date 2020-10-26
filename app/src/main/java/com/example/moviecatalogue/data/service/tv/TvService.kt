package com.example.moviecatalogue.data.service.tv

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse

interface TvService {

    fun getPopularTv(): LiveData<ApiResponse<PopularTvResponse>>
    fun getTvDetails(tvId: Int): LiveData<ApiResponse<TvDetailResponse>>
}