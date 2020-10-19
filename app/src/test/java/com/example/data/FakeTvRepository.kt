package com.example.data

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.service.ApiConfig
import com.example.moviecatalogue.service.datamodel.GenresItemResponse
import com.example.moviecatalogue.service.datamodel.tv.PopularTvItemResponse
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.service.datamodel.tv.TvDetailResponse

object FakeTvRepository {

    fun getTvDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..10) {
            movies.add(
                Film(
                    R.drawable.poster_arrow,
                    "Arrow $x",
                    "2012",
                    250.34,
                    500,
                    "19/10/2019",
                    "Crime, Drama, Mystery, Action & Adventure",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow."
                )
            )
        }
        return movies
    }

    private fun getTvItemResponseDummyData(): ArrayList<PopularTvItemResponse> {
        val movies = arrayListOf<PopularTvItemResponse>()
        for (x in 1..15) {
            movies.add(
                PopularTvItemResponse(
                    x+1,
                    ApiConfig.BASE_IMG_PATH + "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
                    "The boys",
                    927.83,
                    "19/10/2019",
                    235,
                )
            )
        }
        return movies
    }

    fun getPopularMovieResponse(): PopularTvResponse {
        return PopularTvResponse(
            results = getTvItemResponseDummyData()
        )
    }

    fun getTvDetailResponse(): TvDetailResponse {
        return TvDetailResponse(
            1,
            ApiConfig.BASE_IMG_PATH + "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "The Boys",
            927.83,
            "this is overview",
            "19/10/2019",
            listOf(GenresItemResponse("Horror"), GenresItemResponse("Comedy")),
            235
        )
    }
}