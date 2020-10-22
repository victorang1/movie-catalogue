package com.example.moviecatalogue.di.modules

import com.example.moviecatalogue.repository.IFavoriteRepository
import com.example.moviecatalogue.repository.IMovieRepository
import com.example.moviecatalogue.repository.ITvShowRepository
import com.example.moviecatalogue.ui.detail.FilmDetailViewModel
import com.example.moviecatalogue.ui.favorite.FavoriteViewModel
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

    fun providesFilmDetailViewModel(
        movieRepository: IMovieRepository,
        tvShowRepository: ITvShowRepository,
        favoriteRepository: IFavoriteRepository
    ): FilmDetailViewModel {
        return FilmDetailViewModel(movieRepository, tvShowRepository, favoriteRepository)
    }

    fun providesFavoriteViewModel(favoriteRepository: IFavoriteRepository): FavoriteViewModel {
        return FavoriteViewModel(favoriteRepository)
    }

    viewModel { providesMovieViewModel(get()) }
    viewModel { providesTvShowViewModel(get()) }
    viewModel { providesFilmDetailViewModel(get(), get(), get()) }
    viewModel { providesFavoriteViewModel(get()) }
}