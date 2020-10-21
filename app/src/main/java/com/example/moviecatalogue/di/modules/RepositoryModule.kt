package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.data.local.LocalFavoriteSource
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.service.movie.MovieService
import com.example.moviecatalogue.data.service.tv.TvService
import com.example.moviecatalogue.repository.*
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

    fun providesFavoriteRepository(localFavoriteSource: LocalFavoriteSource): IFavoriteRepository =
        FavoriteRepository(localFavoriteSource)

    single { providesMovieRepository(get(), get()) }
    single { providesTvShowRepository(get(), get()) }
    single { providesFavoriteRepository(get()) }
}