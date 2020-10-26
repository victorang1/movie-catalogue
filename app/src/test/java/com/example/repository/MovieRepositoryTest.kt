package com.example.repository

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.paging.DataSource
import com.example.data.FakeMovieRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.LocalFilmSource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.data.service.movie.MovieServiceImpl
import com.example.moviecatalogue.repository.MovieRepository
import com.example.util.PagedListUtil
import org.jetbrains.spek.api.Spek
import org.junit.Assert.*
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito.*

@RunWith(JUnitPlatform::class)
class MovieRepositoryTest : Spek({

    group("Movie Repository") {

        val movieService = mock(MovieServiceImpl::class.java)
        val localFilmSource = mock(LocalFilmSource::class.java)
        val movieRepository = MovieRepository(movieService, localFilmSource)

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

        test("MovieRepository GetMovieData") {
            val dataSource = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Film>
            `when`(localFilmSource.getAllMovies()).thenReturn(dataSource)
            movieRepository.getMovieData()

            val movies =
                Resource.Success(PagedListUtil.mockPagedList(FakeMovieRepository.getMovieDummyData()))
            verify(localFilmSource).getAllMovies()
            assertNotNull(movies.data)
            assertEquals(
                FakeMovieRepository.getRemoteMovieDummyData().count().toLong(),
                movies.data?.count()?.toLong()
            )
        }

        test("MovieRepository GetMovieDetails") {
            movieRepository.getMovieDetails(0)
            verify(localFilmSource).getMovieById(0)
        }
    }
})