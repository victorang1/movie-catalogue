package com.example.moviecatalogue.common

import com.example.moviecatalogue.R
import com.example.moviecatalogue.constant.AppConstant.resources
import org.koin.core.KoinComponent
import retrofit2.Response
import java.lang.Exception

object ApiHelper : KoinComponent {

    fun <T> handleOnResponse(response: Response<T>): ApiResponse<T> {
        return if (response.isSuccessful) {
            try {
                ApiResponse.Success(response.body()!!)
            } catch (e: Exception) {
                ApiResponse.Error(
                    e.message
                        ?: resources.getString(R.string.text_network_error)
                )
            }
        } else {
            ApiResponse.Error(
                resources.getString(R.string.text_network_error)
            )
        }
    }

    fun <T> handleOnFailure(t: Throwable): ApiResponse<T> {
        return ApiResponse.Error(
            t.message
                ?: resources.getString(R.string.text_network_error)
        )
    }
}