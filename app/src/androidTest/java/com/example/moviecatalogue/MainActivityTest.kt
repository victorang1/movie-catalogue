package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.data.FakeMovieRepository
import com.example.data.FakeTvRepository
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.service.movie.MovieServiceImpl
import com.example.moviecatalogue.service.tv.TvServiceImpl
import com.example.moviecatalogue.ui.home.MainActivity
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val movieRepository = FakeMovieRepository()
    private val tvRepository = FakeTvRepository()

    private val dummyMovies = movieRepository.getMovieDummyData()
    private val dummyTvShows = tvRepository.getTvDummyData()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun clear() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        onView(withId(R.id.rv_movies))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_movies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovies() {
        onView(withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.iv_thumbnail))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_popularity))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_votes))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category_text))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_overview_display))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadTvShows() {
        onView(withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShows.size
            )
        )
    }

    @Test
    fun loadDetailShows() {
        onView(withText("Tv Shows")).perform(ViewActions.click())
        onView(withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        onView(withId(R.id.iv_thumbnail))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_popularity))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_votes))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category_text))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_overview_display))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun loadNoDataMovies() {
        val recyclerView = activityRule.activity.findViewById<RecyclerView>(R.id.rv_movies)
        if (recyclerView.adapter?.itemCount == 0) {
            onView(withId(R.id.rv_movies)).check(ViewAssertions.matches(not(isDisplayed())))
            onView(withId(R.id.tv_message)).check(ViewAssertions.matches(isDisplayed()))
        }
    }
}