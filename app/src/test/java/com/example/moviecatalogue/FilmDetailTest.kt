package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeMovieRepository
import com.example.data.FakeTvRepository
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.data.service.ApiHandler
import com.example.moviecatalogue.data.service.datamodel.movie.MovieDetailResponse
import com.example.moviecatalogue.data.service.datamodel.tv.TvDetailResponse
import com.example.moviecatalogue.data.service.movie.MovieServiceImpl
import com.example.moviecatalogue.data.service.tv.TvServiceImpl
import com.example.moviecatalogue.ui.detail.FilmDetailViewModel
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

@RunWith(JUnitPlatform::class)
class FilmDetailTest : Spek({

    group("Film Detail Test") {

        group("Checking Service get relevant data") {

            val movieService = Mockito.mock(MovieServiceImpl::class.java)
            val tvService = Mockito.mock(TvServiceImpl::class.java)
            val movieRepository = MovieRepository(movieService)
            val tvShowRepository = TvShowRepository(tvService)

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

            test("Checking Service GetDetails Type Movie") {
                doAnswer { invocation ->
                    (invocation.arguments[2] as ApiHandler<MovieDetailResponse>)
                        .onSuccess(FakeMovieRepository.getMovieDetailResponse())
                }.`when`(movieService).getMovieDetails(eq(BuildConfig.API_KEY), eq(1), any())
                val movies = LiveDataTestUtil.getValue(movieRepository.getMovieDetails(1))
                Assert.assertNotNull(movies)
                Assert.assertEquals("2067", movies?.title)
            }

            test("Checking Service GetDetails Type TvShow") {
                doAnswer { invocation ->
                    (invocation.arguments[2] as ApiHandler<TvDetailResponse>)
                        .onSuccess(FakeTvRepository.getTvDetailResponse())
                }.`when`(tvService).getTvDetails(eq(BuildConfig.API_KEY), eq(1), any())
                val tvs = LiveDataTestUtil.getValue(tvShowRepository.getTvDetails(1))
                Assert.assertNotNull(tvs)
                Assert.assertEquals("The Boys", tvs?.title)
            }
        }

        group("Checking Repository get relevant data") {

            val movieRepo = Mockito.mock(MovieRepository::class.java)
            val tvRepo = Mockito.mock(TvShowRepository::class.java)

            val detailViewModel = FilmDetailViewModel(movieRepo, tvRepo)

            val observer: Observer<Film> = mock()

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

            test("Movie Repository GetMovieDetails") {
                val dummyMovie = FakeMovieRepository.getMovieDummyData()[0]
                val movieData = MutableLiveData<Film>()
                movieData.value = dummyMovie

                Mockito.`when`(movieRepo.getMovieDetails(0)).thenReturn(movieData)
                val movie = detailViewModel.loadDataById(0, R.string.text_type_movie).value
                Mockito.verify(movieRepo).getMovieDetails(0)
                Assert.assertNotNull(movie)
                Assert.assertEquals("A Star Is Born 1", movie?.title)

                detailViewModel.loadDataById(0, R.string.text_type_movie).observeForever(observer)
                Mockito.verify(observer).onChanged(FakeMovieRepository.getMovieDummyData()[0])
            }

            test("TvShow Repository GetTvDetails") {
                val dummyTvShow = FakeTvRepository.getTvDummyData()[0]
                val tvData = MutableLiveData<Film>()
                tvData.value = dummyTvShow

                Mockito.`when`(tvRepo.getTvDetails(0)).thenReturn(tvData)
                val tvShows = detailViewModel.loadDataById(0, R.string.text_type_tv_show).value
                Mockito.verify(tvRepo).getTvDetails(0)
                Assert.assertNotNull(tvShows)
                Assert.assertEquals("Arrow 1", tvShows?.title)

                detailViewModel.loadDataById(0, R.string.text_type_tv_show).observeForever(observer)
                Mockito.verify(observer).onChanged(FakeTvRepository.getTvDummyData()[0])
            }
        }
    }
})