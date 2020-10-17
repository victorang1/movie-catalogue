package com.example.moviecatalogue

import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.ui.movie.MovieViewModel
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class MovieTest : Spek ({

    group("Movie Group Test") {

        val movieRepository = MovieRepository()
        val viewModel = MovieViewModel(movieRepository)

        test("Repository GetMovieData") {
            Assert.assertNotNull(movieRepository.getMovieData())
            Assert.assertEquals(10, movieRepository.getMovieData().count())
        }

        test("GetMovieData Function Return Size") {
            Assert.assertNotNull(viewModel.getMovieData())
            Assert.assertEquals(10, viewModel.getMovieData().count())
        }
    }
})