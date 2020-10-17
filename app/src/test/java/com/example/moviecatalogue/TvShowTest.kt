package com.example.moviecatalogue

import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
class TvShowTest : Spek({

    group("TvShow Group Test") {

        val tvShowRepository = TvShowRepository()
        val viewModel = TvShowViewModel(tvShowRepository)

        test("Repository GetTvShowData") {
            Assert.assertNotNull(tvShowRepository.getTvShowData())
            Assert.assertEquals(10, tvShowRepository.getTvShowData().count())
        }

        test("ViewModel GetTvShowData") {
            Assert.assertNotNull(viewModel.getTvShowData())
            Assert.assertEquals(10, viewModel.getTvShowData().count())
        }
    }
})