package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import com.example.data.FakeMovieRepository
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.service.movie.MovieServiceImpl
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.util.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class MovieTest : Spek({

    group("Movie Group Test") {

        val movieService = Mockito.mock(MovieServiceImpl::class.java)
        val movieRepository = MovieRepository(movieService)
        val viewModel = MovieViewModel(movieRepository)

        beforeEachTest {
            ArchTaskExecutor.getInstance().setDelegate(object : TaskExecutor() {
                override fun executeOnDiskIO(runnable: Runnable) {
                    runnable.run()
                }

                override fun postToMainThread(runnable: Runnable) {
                    runnable.run()
                }

                override fun isMainThread(): Boolean {
                    return true
                }
            })
        }

        afterEachTest { ArchTaskExecutor.getInstance().setDelegate(null) }

        test("Repository GetMovieData") {
            doAnswer { invocation ->
                (invocation.arguments[1] as ApiHandler<PopularMovieResponse>)
                    .onSuccess(FakeMovieRepository.getPopularMovieResponse())
            }.`when`(movieService).getPopularMovies(eq(API_KEY), any())
            val movies = LiveDataTestUtil.getValue(movieRepository.getMovieData())
            Assert.assertNotNull(movies)
            Assert.assertEquals(15, movies?.count())
        }

        test("GetMovieData Function Return Size") {
            val dummyMovies = MutableLiveData<List<Film>>()
            dummyMovies.value = FakeMovieRepository.getMovieDummyData()
            Assert.assertNotNull(viewModel.getMovieData().value)
            Assert.assertEquals(15, viewModel.getMovieData().value?.count())
        }
    }
})