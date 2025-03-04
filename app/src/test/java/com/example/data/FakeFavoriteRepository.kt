package com.example.data

import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.data.service.ApiConfig
import java.util.*

object FakeFavoriteRepository {

    fun getOneFavoriteDummyData(): Favorite {
        return Favorite(
            "1",
            1,
            "Movie",
            ApiConfig.BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "A Star Is Born 1",
            "Drama, Romance, Music",
            "23/10/2020",
            "This is example of overview"
        )
    }

    fun getFavoriteDummyData(): List<Favorite> {
        val favorites = arrayListOf<Favorite>()
        for (x in 1..15) {
            favorites.add(
                Favorite(
                    UUID.randomUUID().toString(),
                    1,
                    "Movie",
                    ApiConfig.BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
                    "A Star Is Born 1",
                    "Drama, Romance, Music",
                    "23/10/2020",
                    "This is example of overview"
                )
            )
        }
        return favorites
    }
}