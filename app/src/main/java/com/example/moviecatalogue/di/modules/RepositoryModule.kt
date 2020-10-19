package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.service.movie.MovieService
import org.koin.dsl.module

val repositoryModule = module {

    fun providesMovieRepository(movieService: MovieService): IMovieRepository =
        MovieRepository(movieService)

    fun providesTvShowRepository(): ITvShowRepository = TvShowRepository()

    single { providesMovieRepository(get()) }
    single { providesTvShowRepository() }
}