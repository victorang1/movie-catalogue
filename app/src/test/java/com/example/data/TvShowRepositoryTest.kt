package com.example.data

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.tv.TvServiceImpl
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.util.LiveDataTestUtil
import com.example.util.PagedListUtil
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito


@RunWith(JUnitPlatform::class)
class TvShowRepositoryTest : Spek({

    group("TvShow Repository") {

        val tvService = Mockito.mock(TvServiceImpl::class.java)
        val localFilmSource = Mockito.mock(LocalFilmSource::class.java)
        val tvRepository = TvShowRepository(tvService, localFilmSource)

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

        test("TvShowRepository GetTvShowData") {
            val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Film>
            Mockito.`when`(localFilmSource.getAllTvShows()).thenReturn(dataSource)
            tvRepository.getTvShowData()

            val tvs =
                Resource.Success(PagedListUtil.mockPagedList(FakeTvRepository.getTvDummyData()))
            Mockito.verify(localFilmSource).getAllTvShows()
            Assert.assertNotNull(tvs.data)
            Assert.assertEquals(
                FakeTvRepository.getTvDummyData().count().toLong(),
                tvs.data?.count()?.toLong()
            )
        }

        test("TvShowRepository GetTvDetails") {
            val movieData = MutableLiveData<Film>()
            movieData.value = FakeTvRepository.getOneTvDummyData()
            Mockito.`when`(localFilmSource.getTvShowById(0)).thenReturn(movieData)

            val tv = LiveDataTestUtil.getValue(tvRepository.getTvDetails(0))
            Mockito.verify(localFilmSource).getTvShowById(0)
            Assert.assertNotNull(tv?.data)
            Assert.assertEquals("Arrow 1", tv?.data?.title)
        }
    }
})