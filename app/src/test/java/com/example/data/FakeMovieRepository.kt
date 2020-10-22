package com.example.data

import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.ApiConfig.BASE_IMG_PATH

internal object FakeMovieRepository {

    fun getMovieDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..15) {
            movies.add(
                Film(
                    x,
                    "$BASE_IMG_PATH/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "A Star Is Born $x",
                    927.83,
                    235,
                    "19/10/2019",
                    "Drama, Romance, Music",
                    "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                    "Movie"
                )
            )
        }
        return movies
    }

    fun getOneMovieDummyData(): Film {
        return Film(
                    1,
            "$BASE_IMG_PATH/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "A Star Is Born 1",
                    927.83,
                    235,
                    "19/10/2019",
                    "Drama, Romance, Music",
                    "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                    "Movie"
                )
    }

    fun getRemoteMovieDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..15) {
            movies.add(
                Film(
                    x,
                    "$BASE_IMG_PATH/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "A Star Is Born $x",
                    927.83,
                    235,
                    "19/10/2019",
                    "Drama, Romance, Music",
                    "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                    "Movie"
                )
            )
        }
        return movies
    }
}