package com.example.data

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.Film

class FakeMovieRepository {

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
}