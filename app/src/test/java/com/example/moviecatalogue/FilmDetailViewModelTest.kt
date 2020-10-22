package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeMovieRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.FavoriteRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.ui.detail.FilmDetailViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@RunWith(JUnitPlatform::class)
class FilmDetailViewModelTest : Spek({

    group("Film Detail ViewModel") {

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

        val movieRepository = Mockito.mock(MovieRepository::class.java)
        val tvRepository = Mockito.mock(TvShowRepository::class.java)
        val favoriteRepository = Mockito.mock(FavoriteRepository::class.java)
        val filmDetailViewModel = FilmDetailViewModel(movieRepository, tvRepository, favoriteRepository)
        val observer: Observer<Resource<Film>> = mock()

        test("Film Detail ViewModel LoadDataById Type Movie") {
            val dummyResultMovies = Resource.Success(FakeMovieRepository.getOneMovieDummyData())
            val dummyLiveData = MutableLiveData<Resource<Film>>()
            dummyLiveData.value = dummyResultMovies

//            `when`(movieRepository.getMovieDetails(1)).thenReturn(dummyLiveData)
//            val moviesResult = filmDetailViewModel.loadDataById(1, "Movie").value?.data
//            verify(movieRepository).getMovieDetails(1)
//            assertNotNull(moviesResult)
//            assertEquals("A Star Is Born 1", moviesResult?.title)
//
//            filmDetailViewModel.loadDataById(1, "Movie").observeForever(observer)
//            verify(observer).onChanged(dummyResultMovies)
        }
    }
})