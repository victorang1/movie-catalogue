package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.data.service.movie.MovieService
import com.example.moviecatalogue.data.service.tv.TvService
import org.koin.dsl.module

val repositoryModule = module {

    fun providesMovieRepository(
        movieService: MovieService,
        localFilmSource: LocalFilmSource
    ): IMovieRepository =
        MovieRepository(movieService, localFilmSource)

    fun providesTvShowRepository(
        tvService: TvService,
        localFilmSource: LocalFilmSource
    ): ITvShowRepository =
        TvShowRepository(tvService, localFilmSource)

    single { providesMovieRepository(get(), get()) }
    single { providesTvShowRepository(get(), get()) }
}