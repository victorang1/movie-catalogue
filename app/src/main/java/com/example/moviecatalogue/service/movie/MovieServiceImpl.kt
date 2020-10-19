package com.example.moviecatalogue.service.movie

import android.content.res.Resources
import com.example.moviecatalogue.R
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.PopularMovieResponse
import org.koin.core.KoinComponent
import org.koin.core.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MovieServiceImpl(private val movieEndpoint: MovieEndpoint) : MovieService, KoinComponent {

    private val resources: Resources by inject()

    override fun getPopularMovies(apiKey: String, apiHandler: ApiHandler<PopularMovieResponse>) {
        movieEndpoint.getPopularMovies(apiKey).enqueue(object: Callback<PopularMovieResponse> {

            override fun onResponse(
                call: Call<PopularMovieResponse>,
                response: Response<PopularMovieResponse>
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

            override fun onFailure(call: Call<PopularMovieResponse>, t: Throwable) {
                apiHandler.onFailure(t)
            }
        })
    }

    override fun getMovieDetails(
        apiKey: String,
        movieId: Int,
        apiHandler: ApiHandler<MovieDetailResponse>
    ) {
        movieEndpoint.getMovieDetails(apiKey, movieId).enqueue(object: Callback<MovieDetailResponse> {

            override fun onResponse(
                call: Call<MovieDetailResponse>,
                response: Response<MovieDetailResponse>
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

            override fun onFailure(call: Call<MovieDetailResponse>, t: Throwable) {
                apiHandler.onFailure(t)
            }
        })
    }
}