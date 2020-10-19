package com.example.moviecatalogue.service.tv

import android.content.res.Resources
import com.example.moviecatalogue.R
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.service.datamodel.tv.TvDetailResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class TvServiceImpl(private val tvEndpoint: TvEndpoint) : TvService, KoinComponent {

    private val resources: Resources by inject()

    override fun getPopularTv(
        apiKey: String,
        apiHandler: ApiHandler<PopularTvResponse>
    ) {
        tvEndpoint.getPopularTv(apiKey).enqueue(object:
            Callback<PopularTvResponse> {

            override fun onResponse(
                call: Call<PopularTvResponse>,
                response: Response<PopularTvResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        apiHandler.onSuccess(response.body()!!)
                    } catch (e: Exception) {
                        apiHandler.onFailure(e)
                    }
                }
                else {
                    apiHandler.onFailure(Throwable(resources.getString(R.string.text_network_error)))
                }
            }

            override fun onFailure(call: Call<PopularTvResponse>, t: Throwable) {
                apiHandler.onFailure(t)
            }
        })
    }

    override fun getTvDetails(apiKey: String, tvId: Int, apiHandler: ApiHandler<TvDetailResponse>) {
        tvEndpoint.getTvDetails(tvId, apiKey).enqueue(object: Callback<TvDetailResponse> {

            override fun onResponse(
                call: Call<TvDetailResponse>,
                response: Response<TvDetailResponse>
            ) {
                if (response.isSuccessful) {
                    try {
                        apiHandler.onSuccess(response.body()!!)
                    } catch (e: Exception) {
                        apiHandler.onFailure(e)
                    }
                }
                else {
                    apiHandler.onFailure(Throwable(resources.getString(R.string.text_network_error)))
                }
            }

            override fun onFailure(call: Call<TvDetailResponse>, t: Throwable) {
                apiHandler.onFailure(t)
            }
        })
    }
}