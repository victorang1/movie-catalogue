package com.example.moviecatalogue.ui.favorite

import com.example.moviecatalogue.data.local.entity.Favorite

interface FavoriteItemClickCallback {

    fun onRemoveClick(favorite: Favorite)
    fun onItemClick(favorite: Favorite)
}