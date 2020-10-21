package com.example.moviecatalogue.repository

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.moviecatalogue.common.ApiResponse
import com.example.moviecatalogue.common.NetworkBoundResource
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse
import com.example.moviecatalogue.data.service.tv.TvService
import com.example.moviecatalogue.utils.ResponseHelper

class TvShowRepository(
    private val tvService: TvService,
    private val localFilmSource: LocalFilmSource
) : ITvShowRepository {

    override fun getTvShowData(): LiveData<Resource<PagedList<Film>>> {
        return object : NetworkBoundResource<PagedList<Film>, PopularTvResponse>() {
            override fun loadFromDB(): LiveData<PagedList<Film>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localFilmSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<Film>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<PopularTvResponse>> =
                tvService.getPopularTv()

            override fun saveCallResult(data: PopularTvResponse) {
                val movies = ResponseHelper.convertToFilm(data)
                localFilmSource.insertFilm(movies)
            }
        }.toLiveData()
    }

    override fun getTvDetails(tvId: Int): LiveData<Resource<Film>> {
        return object : NetworkBoundResource<Film, TvDetailResponse>() {
            override fun loadFromDB(): LiveData<Film> =
                localFilmSource.getTvShowById(tvId)

            override fun shouldFetch(data: Film?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<TvDetailResponse>> =
                tvService.getTvDetails(tvId)

            override fun saveCallResult(data: TvDetailResponse) {
                val movie = ResponseHelper.convertToFilm(data)
                localFilmSource.updateFilm(movie)
            }
        }.toLiveData()
    }
}