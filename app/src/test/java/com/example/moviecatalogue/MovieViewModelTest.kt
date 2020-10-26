package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.ui.movie.MovieViewModel
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
class MovieViewModelTest : Spek({

    group("Movie ViewModel") {

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
        val movieViewModel = MovieViewModel(movieRepository)
        val observer: Observer<Resource<PagedList<Film>>> = mock()
        val movieList: PagedList<Film> = mock()

        test("Movie ViewModel GetMovieData") {
            val dummyMovies = Resource.Success(movieList)
            `when`(dummyMovies.data?.size).thenReturn(5)
            val movies = MutableLiveData<Resource<PagedList<Film>>>()
            movies.value = dummyMovies

            `when`(movieRepository.getMovieData()).thenReturn(movies)
            val moviesResult = movieViewModel.getMovieData().value?.data
            verify(movieRepository).getMovieData()
            assertNotNull(moviesResult)
            assertEquals(5, moviesResult?.count())

            movieViewModel.getMovieData().observeForever(observer)
            verify(observer).onChanged(dummyMovies)
        }
    }
})