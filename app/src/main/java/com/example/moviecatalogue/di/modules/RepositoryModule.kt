package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.data.service.movie.MovieService
import com.example.moviecatalogue.data.service.tv.TvService
import org.koin.dsl.module

val repositoryModule = module {

    fun providesMovieRepository(movieService: MovieService): IMovieRepository =
        MovieRepository(movieService)

    fun providesTvShowRepository(tvService: TvService): ITvShowRepository =
        TvShowRepository(tvService)

    single { providesMovieRepository(get()) }
    single { providesTvShowRepository(get()) }
}