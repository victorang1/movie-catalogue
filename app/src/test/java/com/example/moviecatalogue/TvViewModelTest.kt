package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.ui.tvshow.TvShowViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class TvViewModelTest : Spek({

    group("TvShow ViewModel") {

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

        val tvRepository = Mockito.mock(TvShowRepository::class.java)
        val tvViewModel = TvShowViewModel(tvRepository)
        val observer: Observer<Resource<PagedList<Film>>> = mock()
        val tvList: PagedList<Film> = mock()

        test("TvShow ViewModel GetTvShowData") {
            val dummyTvShow = Resource.Success(tvList)
            Mockito.`when`(dummyTvShow.data?.size).thenReturn(5)
            val tvShow = MutableLiveData<Resource<PagedList<Film>>>()
            tvShow.value = dummyTvShow

            Mockito.`when`(tvRepository.getTvShowData()).thenReturn(tvShow)
            val moviesResult = tvViewModel.getTvShowData().value?.data
            verify(tvRepository).getTvShowData()
            assertNotNull(moviesResult)
            assertEquals(5, moviesResult?.count())

            tvViewModel.getTvShowData().observeForever(observer)
            verify(observer).onChanged(dummyTvShow)
        }
    }
})