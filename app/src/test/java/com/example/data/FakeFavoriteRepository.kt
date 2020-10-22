package com.example.data

import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.ApiConfig

object FakeFavoriteRepository {

    fun getOneFavoriteDummyData(): Favorite {
        return Favorite(
            "1",
            1,
            "Movie",
            ApiConfig.BASE_IMG_PATH + "/mY7SeH4HFFxW1hiI6cWuwCRKptN.jpg",
            "A Star Is Born 1",
            "Drama, Romance, Music"
        )
    }
}