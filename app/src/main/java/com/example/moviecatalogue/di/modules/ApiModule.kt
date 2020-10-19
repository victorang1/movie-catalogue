package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.service.movie.MovieEndpoint
import com.example.moviecatalogue.service.movie.MovieService
import com.example.moviecatalogue.service.movie.MovieServiceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideMovieEndpoint(retrofit: Retrofit) : MovieEndpoint {
        return retrofit.create(MovieEndpoint::class.java)
    }

    fun provideMovieService(movieEndpoint: MovieEndpoint) : MovieService {
        return MovieServiceImpl(movieEndpoint)
    }

    factory { provideMovieEndpoint(get()) }
    single { provideMovieService(get()) }
}