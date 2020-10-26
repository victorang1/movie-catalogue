package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.ui.home.MainActivity
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FilmDetailActivityTest {

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
    fun loadDetailMovies() {
        Espresso.onView(ViewMatchers.withId(R.id.rv_movies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.iv_thumbnail))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_popularity))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_votes))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_category_text))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_category))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview_display))
            .check(matches(isDisplayed()))
    }

    @Test
    fun loadDetailShows() {
        Espresso.onView(withText("Tv Shows")).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.rv_shows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(ViewMatchers.withId(R.id.iv_thumbnail))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_title))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_year))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_popularity))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_votes))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_category_text))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_category))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
            .check(matches(isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.tv_overview_display))
            .check(matches(isDisplayed()))
    }
}