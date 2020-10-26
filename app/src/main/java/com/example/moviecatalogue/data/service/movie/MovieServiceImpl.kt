package com.example.moviecatalogue.data.service.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.common.ApiHelper
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.koin.core.KoinComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieServiceImpl(private val movieEndpoint: MovieEndpoint) : MovieService, KoinComponent {

    override fun getPopularMovies(): LiveData<ApiResponse<PopularMovieResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<PopularMovieResponse>>()
        movieEndpoint.getPopularMovies(API_KEY).enqueue(object : Callback<PopularMovieResponse> {

            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
            ) {
                EspressoIdlingResource.decrement()
                result.value = ApiHelper.handleOnResponse(response)
            }

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                EspressoIdlingResource.decrement()
                result.value = ApiHelper.handleOnFailure(t)
            }
        })
        return result
    }

    override fun getMovieDetails(movieId: Int): LiveData<ApiResponse<MovieDetailResponse>> {
        EspressoIdlingResource.increment()
        val result = MutableLiveData<ApiResponse<MovieDetailResponse>>()
        movieEndpoint.getMovieDetails(movieId, API_KEY)
            .enqueue(object : Callback<MovieDetailResponse> {

                override fun onResponse(
                    call: Call<MovieDetailResponse>,
                    response: Response<MovieDetailResponse>
                ) {
                    EspressoIdlingResource.decrement()
                    result.value = ApiHelper.handleOnResponse(response)
                }

                override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                    EspressoIdlingResource.decrement()
                    result.value = ApiHelper.handleOnFailure(t)
                }
            })
        return result
    }
}