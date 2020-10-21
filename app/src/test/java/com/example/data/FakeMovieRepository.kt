package com.example.data

import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.data.service.ApiConfig.BASE_IMG_PATH
import com.example.moviecatalogue.data.service.datamodel.GenresItemResponse
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieItemResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse

internal object FakeMovieRepository {

    fun getMovieDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..15) {
            movies.add(
                Film(
                    x,
                    BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "A Star Is Born $x",
                    927.83,
                    235,
                    "19/10/2019",
                    "Drama, Romance, Music",
                    "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons."
                )
            )
        }
        return movies
    }

    private fun getMovieItemResponseDummyData(): ArrayList<PopularMovieItemResponse> {
        val movies = arrayListOf<PopularMovieItemResponse>()
        for (x in 1..15) {
            movies.add(
                PopularMovieItemResponse(
                    x + 1,
                    BASE_IMG_PATH + "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
                    "2067",
                    927.83,
                    "19/10/2019",
                    235,
                )
            )
        }
        return movies
    }

    fun getPopularMovieResponse(): PopularMovieResponse {
        return PopularMovieResponse(
            results = getMovieItemResponseDummyData()
        )
    }

    fun getMovieDetailResponse(): MovieDetailResponse {
        return MovieDetailResponse(
            1,
            BASE_IMG_PATH + "/7D430eqZj8y3oVkLFfsWXGRcpEG.jpg",
            "2067",
            927.83,
            "this is overview",
            "19/10/2019",
            listOf(GenresItemResponse("Horror"), GenresItemResponse("Comedy")),
            235
        )
    }
}