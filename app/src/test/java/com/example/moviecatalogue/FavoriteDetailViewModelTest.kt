package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeFavoriteDetailViewModel
import com.example.data.FakeFavoriteRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Favorite
import com.example.moviecatalogue.repository.FavoriteRepository
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitPlatform::class)
class FavoriteDetailViewModelTest : Spek({

    group("FavoriteDetail ViewModel") {

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
        val favoriteViewModel = FakeFavoriteDetailViewModel(favoriteRepository)
        val observer: Observer<Resource<Favorite>> = mock()
        val mockFavorite: Favorite = mock()

        test("Favorite Detail ViewModel LoadDataById Movie") {
            val dummyFavorites = Resource.Success(mockFavorite)
            Mockito.`when`(dummyFavorites.data?.title)
                .thenReturn(FakeFavoriteRepository.getOneFavoriteDummyData().title)
            val movies = MutableLiveData<Resource<Favorite>>()
            movies.value = dummyFavorites

            Mockito.`when`(favoriteRepository.getFavoriteMovieById(1)).thenReturn(movies)
            val favoriteResult = favoriteViewModel.loadDataById(1, "Movie").value?.data
            verify(favoriteRepository).getFavoriteMovieById(1)
            TestCase.assertNotNull(favoriteResult)
            TestCase.assertEquals(FakeFavoriteRepository.getOneFavoriteDummyData().title, favoriteResult?.title)

            favoriteViewModel.loadDataById(1, "Movie").observeForever(observer)
            verify(observer).onChanged(dummyFavorites)
        }

        test("Favorite Detail ViewModel LoadDataById TvShow") {
            val dummyFavorites = Resource.Success(mockFavorite)
            Mockito.`when`(dummyFavorites.data?.title)
                .thenReturn(FakeFavoriteRepository.getOneFavoriteDummyData().title)
            val movies = MutableLiveData<Resource<Favorite>>()
            movies.value = dummyFavorites

            Mockito.`when`(favoriteRepository.getFavoriteTvShowById(1)).thenReturn(movies)
            val favoriteResult = favoriteViewModel.loadDataById(1, "TvShow").value?.data
            verify(favoriteRepository).getFavoriteTvShowById(1)
            TestCase.assertNotNull(favoriteResult)
            TestCase.assertEquals(FakeFavoriteRepository.getOneFavoriteDummyData().title, favoriteResult?.title)

            favoriteViewModel.loadDataById(1, "TvShow").observeForever(observer)
            verify(observer).onChanged(dummyFavorites)
        }
    }
})