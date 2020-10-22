package com.example.repository

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.data.FakeFavoriteRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.LocalFavoriteSource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.FavoriteRepository
import com.example.util.LiveDataTestUtil
import com.example.util.PagedListUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.spek.api.Spek
import org.junit.Assert
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnitPlatform::class)
class FavoriteRepositoryTest : Spek({

    group("Favorite Repository Load Data") {

        val localFavoriteSource = Mockito.mock(LocalFavoriteSource::class.java)
        val favoriteRepository = FavoriteRepository(localFavoriteSource)

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

        test("Favorite Repository GetAllFavoriteMovies") {
            val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Favorite>
            Mockito.`when`(localFavoriteSource.getAllMovies()).thenReturn(dataSource)
            runBlocking {
                favoriteRepository.getAllFavoriteMovies()

                val favorites =
                    Resource.Success(PagedListUtil.mockPagedList(FakeFavoriteRepository.getFavoriteDummyData()))
                Assert.assertNotNull(favorites.data)
                Assert.assertEquals(
                    FakeFavoriteRepository.getFavoriteDummyData().count().toLong(),
                    favorites.data?.count()?.toLong()
                )
            }
        }

        test("Favorite Repository getAllFavoriteTvs") {
            val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Favorite>
            Mockito.`when`(localFavoriteSource.getAllTvShows()).thenReturn(dataSource)
            runBlocking {
                favoriteRepository.getAllFavoriteTvs()

                val favorites =
                    Resource.Success(PagedListUtil.mockPagedList(FakeFavoriteRepository.getFavoriteDummyData()))
                Assert.assertNotNull(favorites.data)
                Assert.assertEquals(
                    FakeFavoriteRepository.getFavoriteDummyData().count().toLong(),
                    favorites.data?.count()?.toLong()
                )
            }
        }
    }

    group("Favorite Repository Search") {

        val localFavoriteSource = Mockito.mock(LocalFavoriteSource::class.java)
        val favoriteRepository = FavoriteRepository(localFavoriteSource)

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

        test("Favorite Repository Search Movies") {
            val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Favorite>
            Mockito.`when`(localFavoriteSource.getFilteredMovies("test")).thenReturn(dataSource)
            runBlocking {
                favoriteRepository.searchMovies("test")

                val favorites =
                    Resource.Success(PagedListUtil.mockPagedList(FakeFavoriteRepository.getFavoriteDummyData()))
                Assert.assertNotNull(favorites.data)
                Assert.assertEquals(
                    FakeFavoriteRepository.getFavoriteDummyData().count().toLong(),
                    favorites.data?.count()?.toLong()
                )
            }
        }

        test("Favorite Repository Search Tv Show") {
            val dataSource = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Favorite>
            Mockito.`when`(localFavoriteSource.getFilteredTvShow("test")).thenReturn(dataSource)
            runBlocking {
                favoriteRepository.searchTvShows("test")

                val favorites =
                    Resource.Success(PagedListUtil.mockPagedList(FakeFavoriteRepository.getFavoriteDummyData()))
                Assert.assertNotNull(favorites.data)
                Assert.assertEquals(
                    FakeFavoriteRepository.getFavoriteDummyData().count().toLong(),
                    favorites.data?.count()?.toLong()
                )
            }
        }
    }

    group("Favorite Repository Others") {

        val localFavoriteSource = Mockito.mock(LocalFavoriteSource::class.java)
        val favoriteRepository = FavoriteRepository(localFavoriteSource)

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

        test("Favorite Repository Insert") {
            CoroutineScope(Dispatchers.IO).launch {
                val favoriteData = MutableLiveData<Boolean>()
                favoriteData.value = true
                val dummyFavorite = FakeFavoriteRepository.getOneFavoriteDummyData()

                val result = LiveDataTestUtil.getValue(favoriteRepository.insertFavorite(dummyFavorite))
                Mockito.verify(localFavoriteSource).insertFilm(dummyFavorite)
                Assert.assertNotNull(result?.data)
                Assert.assertEquals(true, result?.data)
            }
        }

        test("Favorite Repository Delete") {
            CoroutineScope(Dispatchers.IO).launch {
                val favoriteData = MutableLiveData<Boolean>()
                favoriteData.value = true
                val dummyFavorite = FakeFavoriteRepository.getOneFavoriteDummyData()

                val result = LiveDataTestUtil.getValue(favoriteRepository.deleteFromFavorite(dummyFavorite))
                Mockito.verify(localFavoriteSource).deleteFromFavorite(dummyFavorite)
                Assert.assertNotNull(result?.data)
                Assert.assertEquals(true, result?.data)
            }
        }

        test("Favorite Repository Is Favorite Movie") {
            CoroutineScope(Dispatchers.IO).launch {
                val favoriteData = MutableLiveData<Boolean>()
                favoriteData.value = true
                val dummyFavorite = FakeFavoriteRepository.getOneFavoriteDummyData()
                `when`(localFavoriteSource.isFavoriteMovie(1)).thenReturn(dummyFavorite)

                val result = LiveDataTestUtil.getValue(favoriteRepository.isFavoriteMovie(1))
                Mockito.verify(localFavoriteSource).isFavoriteMovie(1)
                Assert.assertNotNull(result?.data)
                Assert.assertEquals(true, result?.data)
            }
        }

        test("Favorite Repository Is Favorite Tv Show") {
            CoroutineScope(Dispatchers.IO).launch {
                val favoriteData = MutableLiveData<Boolean>()
                favoriteData.value = true
                val dummyFavorite = FakeFavoriteRepository.getOneFavoriteDummyData()
                `when`(localFavoriteSource.isFavoriteTvShow(1)).thenReturn(dummyFavorite)

                val result = LiveDataTestUtil.getValue(favoriteRepository.isFavoriteTvShow(1))
                Mockito.verify(localFavoriteSource).isFavoriteTvShow(1)
                Assert.assertNotNull(result?.data)
                Assert.assertEquals(true, result?.data)
            }
        }
    }
})