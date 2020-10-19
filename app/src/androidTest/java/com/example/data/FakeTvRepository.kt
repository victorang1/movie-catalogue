package com.example.data

import com.example.moviecatalogue.R
import com.example.moviecatalogue.model.Film

class FakeTvRepository {

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