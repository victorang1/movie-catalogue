package com.example.moviecatalogue.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.example.moviecatalogue.data.local.entity.Favorite

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite where filmType=:filmType")
    fun getAllFavoritesByType(filmType: String): DataSource.Factory<Int, Favorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: Favorite)

    @Delete
    fun deleteFromFavorite(favorite: Favorite)
}