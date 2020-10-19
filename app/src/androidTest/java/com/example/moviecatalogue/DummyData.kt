package com.example.moviecatalogue

import com.example.moviecatalogue.model.Film

object DummyData {

    fun getMovieDummyData(): ArrayList<Film> {
        val movies = arrayListOf<Film>()
        for (x in 1..15) {
            movies.add(
                Film(
                    R.drawable.poster_a_start_is_born,
                    "A Star Is Born $x",
                    "2018",
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
}