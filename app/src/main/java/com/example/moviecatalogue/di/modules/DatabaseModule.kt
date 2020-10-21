package com.example.moviecatalogue.di.modules

import android.content.Context
import com.example.moviecatalogue.data.LocalFilmSource
import com.example.moviecatalogue.data.local.room.MovieCatalogueDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    fun providesMovieCatalogueDatabase(context: Context) =
        MovieCatalogueDatabase.getInstance(context)

    fun providesLocalFilmSource(db: MovieCatalogueDatabase) =
        LocalFilmSource(db.filmDao())

    single { providesMovieCatalogueDatabase(androidContext()) }
    single { providesLocalFilmSource(get()) }
}