package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.data.FakeFavoriteViewModel
import com.example.data.FakeMovieRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.FavoriteRepository
import com.example.moviecatalogue.utils.ResponseHelper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class FavoriteViewModelTest : Spek({

    group("Favorite ViewModel Movie") {

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

        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val favoriteViewModel = FakeFavoriteViewModel(favoriteRepository)
        val observer: Observer<Resource<PagedList<Favorite>>> = mock()
        val favoriteList: PagedList<Favorite> = mock()

        beforeEachTest {
            favoriteViewModel.setSelectedFilmType("Movie")
        }

        test("Favorite ViewModel GetShowedData Type Movie") {
            val dummyFavorite = Resource.Success(favoriteList)
            val favorite = MutableLiveData<Resource<PagedList<Favorite>>>()
            favorite.value = dummyFavorite

            Mockito.`when`(favoriteRepository.getAllFavoriteMovies()).thenReturn(favorite)

            favoriteViewModel.getShowedData().observeForever(observer)

            verify(observer).onChanged(dummyFavorite)
        }
    }

    group("Favorite ViewModel TvShow") {

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

        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val favoriteViewModel = FakeFavoriteViewModel(favoriteRepository)
        val observer: Observer<Resource<PagedList<Favorite>>> = mock()
        val favoriteList: PagedList<Favorite> = mock()

        beforeEachTest {
            favoriteViewModel.setSelectedFilmType("TvShow")
        }

        test("Favorite ViewModel GetShowedData Type TvShow") {
            val dummyFavorite = Resource.Success(favoriteList)
            val favorite = MutableLiveData<Resource<PagedList<Favorite>>>()
            favorite.value = dummyFavorite

            Mockito.`when`(favoriteRepository.getAllFavoriteTvs()).thenReturn(favorite)

            favoriteViewModel.getShowedData().observeForever(observer)

            verify(observer).onChanged(dummyFavorite)
        }
    }

    group("Favorite ViewModel Search Movie") {

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

        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val favoriteViewModel = FakeFavoriteViewModel(favoriteRepository)
        val observer: Observer<Resource<PagedList<Favorite>>> = mock()
        val favoriteList: PagedList<Favorite> = mock()

        beforeEachTest {
            favoriteViewModel.setSelectedFilmType("Movie")
        }

        test("Favorite ViewModel SearchUser") {
            val dummyFavorites = Resource.Success(favoriteList)
            Mockito.`when`(dummyFavorites.data?.size).thenReturn(5)
            val movies = MutableLiveData<Resource<PagedList<Favorite>>>()
            movies.value = dummyFavorites

            Mockito.`when`(favoriteRepository.searchMovies("test")).thenReturn(movies)
            val favoriteResult = favoriteViewModel.searchUser("test").value?.data
            verify(favoriteRepository).searchMovies("test")
            TestCase.assertNotNull(favoriteResult)
            TestCase.assertEquals(5, favoriteResult?.count())

            favoriteViewModel.searchUser("test").observeForever(observer)
            verify(observer).onChanged(dummyFavorites)
        }
    }

    group("Favorite ViewModel Search TvShow") {

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

        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val favoriteViewModel = FakeFavoriteViewModel(favoriteRepository)
        val observer: Observer<Resource<PagedList<Favorite>>> = mock()
        val favoriteList: PagedList<Favorite> = mock()

        beforeEachTest {
            favoriteViewModel.setSelectedFilmType("TvShow")
        }

        test("Favorite ViewModel SearchUser") {
            val dummyFavorites = Resource.Success(favoriteList)
            Mockito.`when`(dummyFavorites.data?.size).thenReturn(5)
            val movies = MutableLiveData<Resource<PagedList<Favorite>>>()
            movies.value = dummyFavorites

            Mockito.`when`(favoriteRepository.searchTvShows("test")).thenReturn(movies)
            val favoriteResult = favoriteViewModel.searchUser("test").value?.data
            verify(favoriteRepository).searchTvShows("test")
            TestCase.assertNotNull(favoriteResult)
            TestCase.assertEquals(5, favoriteResult?.count())

            favoriteViewModel.searchUser("test").observeForever(observer)
            verify(observer).onChanged(dummyFavorites)
        }
    }

    group("Favorite ViewModel DeleteFromFavorite") {

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

        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val favoriteViewModel = FakeFavoriteViewModel(favoriteRepository)
        val observer: Observer<Resource<Boolean>> = mock()
        val isSuccess: Resource<Boolean> = mock()

        test("Favorite ViewModel DeleteFromFavorite") {
            Mockito.`when`(isSuccess.data).thenReturn(true)
            val dummyLiveData = MutableLiveData<Resource<Boolean>>()
            dummyLiveData.value = isSuccess
            val dummyFilmData = FakeMovieRepository.getOneMovieDummyData()
            val dummyFavoriteData = ResponseHelper.convertToFavorite(dummyFilmData)

            Mockito.`when`(favoriteRepository.deleteFromFavorite(dummyFavoriteData))
                .thenReturn(dummyLiveData)
            val isSuccessResult =
                favoriteViewModel.removeFromFavorite(dummyFavoriteData).value?.data
            Mockito.verify(favoriteRepository).deleteFromFavorite(dummyFavoriteData)
            TestCase.assertNotNull(isSuccessResult)
            TestCase.assertEquals(true, isSuccessResult)

            favoriteViewModel.removeFromFavorite(dummyFavoriteData).observeForever(observer)
            Mockito.verify(observer).onChanged(isSuccess)
        }
    }
})