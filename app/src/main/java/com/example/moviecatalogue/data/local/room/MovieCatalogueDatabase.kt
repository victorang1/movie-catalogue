package com.example.moviecatalogue.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.moviecatalogue.data.local.entity.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object {

        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        fun getInstance(context: Context): MovieCatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, MovieCatalogueDatabase::class.java, "MovieCatalogue.db"
        ).build()
    }
}