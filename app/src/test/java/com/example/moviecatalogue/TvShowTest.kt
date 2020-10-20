package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeTvRepository
import com.example.moviecatalogue.model.Film
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.service.ApiHandler
import com.example.moviecatalogue.service.datamodel.tv.PopularTvResponse
import com.example.moviecatalogue.service.tv.TvServiceImpl
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
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
class TvShowTest : Spek({

    group("TvShow Service Test") {

        val tvService = Mockito.mock(TvServiceImpl::class.java)
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

        test("Service GetTvShowData") {
            doAnswer { invocation ->
                (invocation.arguments[1] as ApiHandler<PopularTvResponse>)
                    .onSuccess(FakeTvRepository.getPopularMovieResponse())
            }.`when`(tvService).getPopularTv(eq(BuildConfig.API_KEY), any())
            val movies = LiveDataTestUtil.getValue(tvShowRepository.getTvShowData())
            Assert.assertNotNull(movies)
            Assert.assertEquals(15, movies?.count())
        }
    }

    group("Tv Show Repository") {

        val tvShowRepository = Mockito.mock(TvShowRepository::class.java)
        val viewModel = TvShowViewModel(tvShowRepository)

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

        test("Repository GetTvShowData") {
            val dummyTvShows = FakeTvRepository.getTvDummyData()
            val tvData = MutableLiveData<List<Film>>()
            tvData.value = dummyTvShows

            Mockito.`when`(tvShowRepository.getTvShowData()).thenReturn(tvData)
            val tvShows = viewModel.getTvShowData().value
            Mockito.verify(tvShowRepository).getTvShowData()
            Assert.assertNotNull(tvShows)
            Assert.assertEquals(10, tvShows?.count())

            viewModel.getTvShowData().observeForever(observer)
            Mockito.verify(observer).onChanged(FakeTvRepository.getTvDummyData())
        }
    }
})