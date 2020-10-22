package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeMovieRepository
import com.example.moviecatalogue.BuildConfig.API_KEY
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.data.service.ApiHandler
import com.example.moviecatalogue.data.service.datamodel.movie.PopularMovieResponse
import com.example.moviecatalogue.data.service.movie.MovieServiceImpl
import com.example.moviecatalogue.ui.movie.MovieViewModel
import com.example.util.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(JUnitPlatform::class)
class MovieTest : Spek({

    group("Movie Service Test") {

        val movieService = Mockito.mock(MovieServiceImpl::class.java)
        val localFilmSource = Mockito.mock(LocalFilmSource::class.java)
        val movieRepository = MovieRepository(movieService, localFilmSource)

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

        test("Service GetMovieData") {
            doAnswer { invocation ->
                (invocation.arguments[0] as ApiHandler<PopularMovieResponse>)
                    .onSuccess(FakeMovieRepository.getPopularMovieResponse())
                null
            }.`when`(movieService).getPopularMovies()
            val movies = LiveDataTestUtil.getValue(movieRepository.getMovieData())
            Assert.assertNotNull(movies)
            Assert.assertEquals(15, movies?.count())
        }
    }

    group("Movie Repository") {

        val movieRepository = Mockito.mock(MovieRepository::class.java)
        val viewModel = MovieViewModel(movieRepository)

        val observer: Observer<List<Film>> = mock()

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
            val dummyMovies = FakeMovieRepository.getMovieDummyData()
            val moviesData = MutableLiveData<List<Film>>()
            moviesData.value = dummyMovies

            `when`(movieRepository.getMovieData()).thenReturn(moviesData)
            val movies = viewModel.getMovieData().value
            verify(movieRepository).getMovieData()
            Assert.assertNotNull(movies)
            Assert.assertEquals(15, movies?.count())

            viewModel.getMovieData().observeForever(observer)
            verify(observer).onChanged(FakeMovieRepository.getMovieDummyData())
        }
    }
})