package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.service.movie.MovieEndpoint
import com.example.moviecatalogue.service.movie.MovieService
import com.example.moviecatalogue.service.movie.MovieServiceImpl
import com.example.moviecatalogue.service.tv.TvEndpoint
import com.example.moviecatalogue.service.tv.TvService
import com.example.moviecatalogue.service.tv.TvServiceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {

    fun provideMovieEndpoint(retrofit: Retrofit) : MovieEndpoint {
        return retrofit.create(MovieEndpoint::class.java)
    }

    fun provideMovieService(movieEndpoint: MovieEndpoint) : MovieService {
        return MovieServiceImpl(movieEndpoint)
    }

    fun provideTvEndpoint(retrofit: Retrofit) : TvEndpoint {
        return retrofit.create(TvEndpoint::class.java)
    }

    fun provideTvService(tvEndpoint: TvEndpoint) : TvService {
        return TvServiceImpl(tvEndpoint)
    }

    factory { provideMovieEndpoint(get()) }
    single { provideMovieService(get()) }

    factory { provideTvEndpoint(get()) }
    single { provideTvService(get()) }
}