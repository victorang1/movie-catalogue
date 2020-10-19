package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.BuildConfig
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.service.datamodel.tv.TvDetailResponse
import com.example.moviecatalogue.service.tv.TvService
import com.example.moviecatalogue.utils.ResponseHelper

class TvShowRepository(private val tvService: TvService) : ITvShowRepository {

    override fun getTvShowData(): LiveData<List<Film>> {
        val movies = MutableLiveData<List<Film>>()
        tvService.getPopularTv(BuildConfig.API_KEY, object :
            ApiHandler<PopularTvResponse> {

            override fun onSuccess(response: PopularTvResponse) {
                movies.value = ResponseHelper.convertToFilm(response)
            }

            override fun onFailure(throwable: Throwable) {
                throw throwable
            }
        })
        return movies
    }

    override fun getTvDetails(tvId: Int): LiveData<Film> {
        val tvs = MutableLiveData<Film>()
        tvService.getTvDetails(BuildConfig.API_KEY, tvId, object : ApiHandler<TvDetailResponse> {

            override fun onSuccess(response: TvDetailResponse) {
                tvs.value = ResponseHelper.convertToFilm(response)
            }

            override fun onFailure(throwable: Throwable) {
                throw throwable
            }
        })
        return tvs
    }
}