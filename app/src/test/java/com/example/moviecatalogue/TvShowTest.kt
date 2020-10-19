package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
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
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class TvShowTest : Spek({

    group("TvShow Group Test") {

        val tvService = Mockito.mock(TvServiceImpl::class.java)
        val tvShowRepository = TvShowRepository(tvService)
        val viewModel = TvShowViewModel(tvShowRepository)

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
            doAnswer { invocation ->
                (invocation.arguments[1] as ApiHandler<PopularTvResponse>)
                    .onSuccess(FakeTvRepository.getPopularMovieResponse())
            }.`when`(tvService).getPopularTv(eq(BuildConfig.API_KEY), any())
            val movies = LiveDataTestUtil.getValue(tvShowRepository.getTvShowData())
            Assert.assertNotNull(movies)
            Assert.assertEquals(15, movies?.count())
        }

        test("ViewModel GetTvShowData") {
            val dummyTvs = MutableLiveData<List<Film>>()
            dummyTvs.value = FakeTvRepository.getTvDummyData()
            Assert.assertNotNull(viewModel.getTvShowData().value)
            Assert.assertEquals(15, viewModel.getTvShowData().value?.count())
        }
    }
})