package com.example.moviecatalogue.data.service.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.common.ApiHelper
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TvServiceImpl(private val tvEndpoint: TvEndpoint) : TvService, KoinComponent {

    override fun getPopularTv(): LiveData<ApiResponse<PopularTvResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<PopularTvResponse>>()
        tvEndpoint.getPopularTv(API_KEY).enqueue(object : Callback<PopularTvResponse> {

            override fun onResponse(
                call: Call<PopularTvResponse>,
                response: Response<PopularTvResponse>
            ) {
                EspressoIdlingResource.decrement()
                result.value = ApiHelper.handleOnResponse(response)
            }

            override fun onFailure(call: Call<PopularTvResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                result.value = ApiHelper.handleOnFailure(t)
            }
        })
        return result
    }

    override fun getTvDetails(tvId: Int): LiveData<ApiResponse<TvDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<TvDetailResponse>>()
        tvEndpoint.getTvDetails(tvId, API_KEY)
            .enqueue(object : Callback<TvDetailResponse> {

                override fun onResponse(
                    call: Call<TvDetailResponse>,
                    response: Response<TvDetailResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    result.value = ApiHelper.handleOnResponse(response)
                }

                override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                    result.value = ApiHelper.handleOnFailure(t)
                }
            })
        return result
    }
}