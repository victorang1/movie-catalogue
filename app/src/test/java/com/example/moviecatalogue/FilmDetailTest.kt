package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import com.example.data.FakeMovieRepository
import com.example.data.FakeTvRepository
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.service.datamodel.tv.TvDetailResponse
import com.example.moviecatalogue.service.movie.MovieServiceImpl
import com.example.moviecatalogue.service.tv.TvServiceImpl
import com.example.moviecatalogue.ui.detail.FilmDetailViewModel
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
class FilmDetailTest : Spek({

    group("Film Detail Test") {

        val movieService = Mockito.mock(MovieServiceImpl::class.java)
        val tvService = Mockito.mock(TvServiceImpl::class.java)
        val movieRepository = MovieRepository(movieService)
        val tvShowRepository = TvShowRepository(tvService)
        val viewModel = FilmDetailViewModel(movieRepository, tvShowRepository)

        group("Checking function get relevant data") {

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

            test("Checking Repository GetDetails Type Movie") {
                doAnswer { invocation ->
                    (invocation.arguments[2] as ApiHandler<MovieDetailResponse>)
                        .onSuccess(FakeMovieRepository.getMovieDetailResponse())
                }.`when`(movieService).getMovieDetails(eq(BuildConfig.API_KEY), eq(1), any())
                val movies = LiveDataTestUtil.getValue(movieRepository.getMovieDetails(1))
                Assert.assertNotNull(movies)
                Assert.assertEquals("2067", movies?.title)
            }

            test("Checking Repository GetDetails Type TvShow") {
                doAnswer { invocation ->
                    (invocation.arguments[2] as ApiHandler<TvDetailResponse>)
                        .onSuccess(FakeTvRepository.getTvDetailResponse())
                }.`when`(tvService).getTvDetails(eq(BuildConfig.API_KEY), eq(1), any())
                val tvs = LiveDataTestUtil.getValue(tvShowRepository.getTvDetails(1))
                Assert.assertNotNull(tvs)
                Assert.assertEquals("The Boys", tvs?.title)
            }

            test("Checking ViewModel LoadDataById Type Movie") {
                val dummyMovies = MutableLiveData<List<Film>>()
                dummyMovies.value = FakeMovieRepository.getMovieDummyData()
                val result = viewModel.loadDataById(1, R.string.text_type_movie)
                Assert.assertNotNull(result.value)
                Assert.assertEquals("2067", result.value?.title)
            }

            test("Checking ViewModel LoadDataById Type Tv") {
                val dummyMovies = MutableLiveData<List<Film>>()
                dummyMovies.value = FakeMovieRepository.getMovieDummyData()
                val result = viewModel.loadDataById(1, R.string.text_type_tv_show)
                Assert.assertNotNull(result.value)
                Assert.assertEquals("The Boys", result.value?.title)
            }
        }
    }
})