package com.example.moviecatalogue.di.modules

import android.content.Context
import com.example.moviecatalogue.data.local.LocalFavoriteSource
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.local.room.MovieCatalogueDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun providesMovieCatalogueDatabase(context: Context) =
        MovieCatalogueDatabase.getInstance(context)

    fun providesLocalFilmSource(db: MovieCatalogueDatabase) =
        LocalFilmSource(db.filmDao())

    fun providesLocalFavoriteSource(db: MovieCatalogueDatabase) =
        LocalFavoriteSource(db.favoriteDao())

    single { providesMovieCatalogueDatabase(androidContext()) }
    single { providesLocalFilmSource(get()) }
    single { providesLocalFavoriteSource(get()) }
}