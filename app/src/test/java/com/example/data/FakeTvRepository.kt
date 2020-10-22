package com.example.data

import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.ApiConfig
import com.example.moviecatalogue.data.service.datamodel.GenresItemResponse
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvItemResponse
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse

internal object FakeTvRepository {

    fun getTvDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..10) {
            movies.add(
                Film(
                    x,
                    ApiConfig.BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "Arrow $x",
                    250.34,
                    500,
                    "19/10/2019",
                    "Crime, Drama, Mystery, Action & Adventure",
                    "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                    "TvShow"
                )
            )
        }
        return movies
    }

    fun getOneTvDummyData(): Film {
        return Film(
            1,
            ApiConfig.BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "Arrow 1",
            250.34,
            500,
            "19/10/2019",
            "Crime, Drama, Mystery, Action & Adventure",
            "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
            "TvShow"
        )
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
}