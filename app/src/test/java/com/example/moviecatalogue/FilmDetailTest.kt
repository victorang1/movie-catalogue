package com.example.moviecatalogue

import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.ui.detail.FilmDetailViewModel
import com.example.moviecatalogue.ui.movie.MovieViewModel
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class FilmDetailTest : Spek({

    group("Film Detail Test") {

        val movieRepository = MovieRepository()
        val tvShowRepository = TvShowRepository()
        val viewModel = FilmDetailViewModel(movieRepository, tvShowRepository)

        group("Checking function get relevant data") {

            test("Checking LoadDataById Type Movie") {
                val result = viewModel.loadDataById(0, R.string.text_type_movie)
                Assert.assertNotNull(result)
                Assert.assertEquals("A Star Is Born", result.title)
            }

            test("Checking LoadDataById Type TvShow") {
                val result = viewModel.loadDataById(0, R.string.text_type_tv_show)
                Assert.assertNotNull(result)
                Assert.assertEquals("Arrow", result.title)
            }
        }
    }
})