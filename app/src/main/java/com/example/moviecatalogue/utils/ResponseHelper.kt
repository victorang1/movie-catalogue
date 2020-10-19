package com.example.moviecatalogue.utils

import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.service.ApiConfig.BASE_IMG_PATH
import com.example.moviecatalogue.service.datamodel.movie.PopularMovieResponse

object ResponseHelper {

    fun convertToFilm(response: PopularMovieResponse): List<Film> {
        val films = mutableListOf<Film>()
        response.results.forEach { itemResponse ->
            val film = Film(
                BASE_IMG_PATH + itemResponse.posterPath,
                itemResponse.title,
                itemResponse.popularity,
                itemResponse.voteCount,
                itemResponse.releaseDate
            )
            films.add(film)
        }
        return films
    }
}