package com.example.moviecatalogue.utils

import com.example.moviecatalogue.constant.AppConstant
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.ApiConfig.BASE_IMG_PATH
import com.example.moviecatalogue.data.service.datamodel.GenresItemResponse
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.data.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse
import java.lang.StringBuilder

object ResponseHelper {

    fun convertToFilm(response: PopularMovieResponse): List<Film> {
        val films = mutableListOf<Film>()
        response.results.forEach { itemResponse ->
            val film = Film(
                itemResponse.id,
                BASE_IMG_PATH + itemResponse.posterPath,
                itemResponse.title ?: "",
                itemResponse.popularity ?: 0.0,
                itemResponse.voteCount ?: 0,
                itemResponse.releaseDate ?: "",
                AppConstant.MOVIE
            )
            films.add(film)
        }
        return films
    }

    fun convertToFilm(response: PopularTvResponse): List<Film> {
        val films = mutableListOf<Film>()
        response.results.forEach { itemResponse ->
            val film = Film(
                itemResponse.id,
                BASE_IMG_PATH + itemResponse.posterPath,
                itemResponse.title ?: "",
                itemResponse.popularity ?: 0.0,
                itemResponse.voteCount ?: 0,
                itemResponse.releaseDate ?: "",
                AppConstant.TV_SHOW
            )
            films.add(film)
        }
        return films
    }

    fun convertToFilm(response: MovieDetailResponse): Film {
        return Film(
            null,
            response.id,
            BASE_IMG_PATH + response.posterPath,
            response.title,
            response.popularity,
            response.voteCount,
            response.release_date,
            getGenres(response.genres),
            response.overview,
            AppConstant.MOVIE
        )
    }

    fun convertToFilm(response: TvDetailResponse): Film {
        return Film(
            null,
            response.id,
            BASE_IMG_PATH + response.posterPath,
            response.title,
            response.popularity,
            response.voteCount,
            response.release_date,
            getGenres(response.genres),
            response.overview,
            AppConstant.TV_SHOW
        )
    }

    private fun getGenres(genres: List<GenresItemResponse>): String {
        val result = StringBuilder()
        var isFirst = true
        for (genre in genres) {
            if (isFirst)
                isFirst = false
            else
                result.append(", ")
            result.append(genre.name)
        }
        return result.toString()
    }
}