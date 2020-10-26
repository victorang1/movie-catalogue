package com.example.moviecatalogue

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.data.FakeFilmDetailViewModel
import com.example.data.FakeMovieRepository
import com.example.data.FakeTvRepository
import com.example.moviecatalogue.common.Resource
import com.example.moviecatalogue.data.local.entity.Film
import com.example.moviecatalogue.repository.FavoriteRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.utils.ResponseHelper
import com.nhaarman.mockitokotlin2.mock
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import org.jetbrains.spek.api.Spek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

@RunWith(JUnitPlatform::class)
class FilmDetailViewModelTest : Spek({

    group("Film Detail ViewModel Load Film") {

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
        val filmDetailViewModel =
            FakeFilmDetailViewModel(movieRepository, tvRepository, favoriteRepository)
        val observer: Observer<Resource<Film>> = mock()
        val film: Film = mock()

        test("Film Detail ViewModel LoadDataById Type Movie") {
            val dummyResultMovies = Resource.Success(film)
            `when`(dummyResultMovies.data?.title).thenReturn(FakeMovieRepository.getOneMovieDummyData().title)
            val dummyLiveData = MutableLiveData<Resource<Film>>()
            dummyLiveData.value = dummyResultMovies

            `when`(movieRepository.getMovieDetails(1)).thenReturn(dummyLiveData)
            val movieTitleResult = filmDetailViewModel.loadDataById(1, "Movie").value?.data?.title
            verify(movieRepository).getMovieDetails(1)
            assertNotNull(movieTitleResult)
            assertEquals("A Star Is Born 1", movieTitleResult)

            filmDetailViewModel.loadDataById(1, "Movie").observeForever(observer)
            verify(observer).onChanged(dummyResultMovies)
        }

        test("Film Detail ViewModel LoadDataById Type Tv") {
            val dummyResultTvs = Resource.Success(film)
            `when`(dummyResultTvs.data?.title).thenReturn(FakeTvRepository.getOneTvDummyData().title)
            val dummyLiveData = MutableLiveData<Resource<Film>>()
            dummyLiveData.value = dummyResultTvs

            `when`(tvRepository.getTvDetails(1)).thenReturn(dummyLiveData)
            val tvTitleResult = filmDetailViewModel.loadDataById(1, "TvShow").value?.data?.title
            verify(tvRepository).getTvDetails(1)
            assertNotNull(tvTitleResult)
            assertEquals("Arrow 1", tvTitleResult)

            filmDetailViewModel.loadDataById(1, "TvShow").observeForever(observer)
            verify(observer).onChanged(dummyResultTvs)
        }
    }

    group("Film Detail ViewModel IsFavorite Movie") {

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
        val filmDetailViewModel =
            FakeFilmDetailViewModel(movieRepository, tvRepository, favoriteRepository)
        val observer: Observer<Resource<Boolean>> = mock()
        val isFavorite: Resource<Boolean> = mock()

        test("Film Detail ViewModel IsFavoriteFilm Type Movie") {
            `when`(isFavorite.data).thenReturn(true)
            val dummyLiveData = MutableLiveData<Resource<Boolean>>()
            dummyLiveData.value = isFavorite

            `when`(favoriteRepository.isFavoriteMovie(1)).thenReturn(dummyLiveData)
            val isFavoriteResult = filmDetailViewModel.isFavoriteFilm(1, "Movie").value?.data
            verify(favoriteRepository).isFavoriteMovie(1)
            assertNotNull(isFavorite)
            assertEquals(true, isFavoriteResult)

            filmDetailViewModel.isFavoriteFilm(1, "Movie").observeForever(observer)
            verify(observer).onChanged(isFavorite)
        }
    }

    group("Film Detail ViewModel IsFavorite Tv Show") {

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
        val filmDetailViewModel =
            FakeFilmDetailViewModel(movieRepository, tvRepository, favoriteRepository)
        val observer: Observer<Resource<Boolean>> = mock()
        val isFavorite: Resource<Boolean> = mock()

        test("Film Detail ViewModel IsFavoriteFilm Type Tv Show") {
            `when`(isFavorite.data).thenReturn(true)
            val dummyLiveData = MutableLiveData<Resource<Boolean>>()
            dummyLiveData.value = isFavorite

            `when`(favoriteRepository.isFavoriteTvShow(1)).thenReturn(dummyLiveData)
            val isFavoriteResult = filmDetailViewModel.isFavoriteFilm(1, "TvShow").value?.data
            verify(favoriteRepository).isFavoriteTvShow(1)
            assertNotNull(isFavorite)
            assertEquals(true, isFavoriteResult)

            filmDetailViewModel.isFavoriteFilm(1, "TvShow").observeForever(observer)
            verify(observer).onChanged(isFavorite)
        }
    }

    group("Film Detail ViewModel AddToFavorite Movie") {

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
        val filmDetailViewModel =
            FakeFilmDetailViewModel(movieRepository, tvRepository, favoriteRepository)
        val observer: Observer<Resource<Boolean>> = mock()
        val isSuccess: Resource<Boolean> = mock()

        test("Film Detail ViewModel AddToFavorite") {
            `when`(isSuccess.data).thenReturn(true)
            val dummyLiveData = MutableLiveData<Resource<Boolean>>()
            dummyLiveData.value = isSuccess
            val dummyFilmData = FakeMovieRepository.getOneMovieDummyData()
            val dummyFavoriteData = ResponseHelper.convertToFavorite(dummyFilmData)

            `when`(favoriteRepository.insertFavorite(dummyFavoriteData)).thenReturn(dummyLiveData)
            val isSuccessResult =
                filmDetailViewModel.addToFavorite(dummyFavoriteData).value?.data
            verify(favoriteRepository).insertFavorite(dummyFavoriteData)
            assertNotNull(isSuccessResult)
            assertEquals(true, isSuccessResult)

            filmDetailViewModel.addToFavorite(dummyFavoriteData).observeForever(observer)
            verify(observer).onChanged(isSuccess)
        }
    }
})