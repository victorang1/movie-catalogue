package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    fun providesMovieViewModel(movieRepository: IMovieRepository): MovieViewModel {
        return MovieViewModel(movieRepository)
    }

    fun providesTvShowViewModel(tvShowRepository: ITvShowRepository): TvShowViewModel {
        return TvShowViewModel(tvShowRepository)
    }

    viewModel { providesMovieViewModel(get()) }
    viewModel { providesTvShowViewModel(get()) }
}