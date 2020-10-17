package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.repository.MovieRepository
import com.example.moviecatalogue.repository.TvShowRepository
import com.example.moviecatalogue.ui.home.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val movieRepository = MovieRepository()
    private val tvShowsRepository = TvShowRepository()
    private val dummyMovies = movieRepository.getMovieData()
    private val dummyTvShows = tvShowsRepository.getTvShowData()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

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
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(withText(dummyMovies[0].year)))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(withText(dummyMovies[0].category)))
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
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_title))
            .check(ViewAssertions.matches(withText(dummyTvShows[0].title)))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_year))
            .check(ViewAssertions.matches(withText(dummyTvShows[0].year)))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.tv_category))
            .check(ViewAssertions.matches(withText(dummyTvShows[0].category)))
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