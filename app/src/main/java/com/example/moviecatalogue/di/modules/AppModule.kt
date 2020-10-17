package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import org.koin.dsl.module

val appModule = module {

    fun providesMovieRepository(): IMovieRepository = MovieRepository()
    fun providesTvShowRepository(): ITvShowRepository = TvShowRepository()

    single { providesMovieRepository() }
    single { providesTvShowRepository() }
}