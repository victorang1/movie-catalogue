package com.example.moviecatalogue

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.ui.favorite.FavoriteActivity
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FavoriteDetailActivityTest {

    @get:Rule
    var activityRule = ActivityTestRule(FavoriteActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun clear() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun recyclerViewTest() {
        if (getRVCount() ?: -1 > 0) {
            Espresso.onView(ViewMatchers.withId(R.id.rv_favorite)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
            Espresso.onView(ViewMatchers.withId(R.id.rv_favorite)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )
            Espresso.onView(ViewMatchers.withId(R.id.iv_thumbnail))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.tv_title))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.tv_release))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.tv_category))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.tv_overview))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
            Espresso.onView(ViewMatchers.withId(R.id.tv_overview_display))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.tv_message)).check(
                ViewAssertions.matches(
                    ViewMatchers.isDisplayed()
                )
            )
        }
    }

    private fun getRVCount(): Int? {
        val recyclerView =
            activityRule.activity.findViewById(R.id.rv_favorite) as RecyclerView
        return recyclerView.adapter?.itemCount
    }
}