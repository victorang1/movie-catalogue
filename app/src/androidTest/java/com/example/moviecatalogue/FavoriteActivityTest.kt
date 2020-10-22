package com.example.moviecatalogue

import android.view.KeyEvent
import android.widget.EditText
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.moviecatalogue.ui.home.MainActivity
import com.example.moviecatalogue.utils.EspressoIdlingResource
import org.hamcrest.Matchers.greaterThan
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FavoriteActivityTest {

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
    fun loadFavoriteItems() {
        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform((ViewActions.click()))
        onView(withId(R.id.rv_favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.rv_favorite)).check(RecyclerViewItemCountAssertion(greaterThan(0)))
    }

    @Test
    fun searchBarShown() {
        onView(withId(R.id.favorite))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.favorite))
            .perform((ViewActions.click()))
        onView(withId(R.id.menu_search))
            .check(ViewAssertions.matches(isDisplayed()))
        onView(withId(R.id.menu_search))
            .perform((ViewActions.click()))
        onView(isAssignableFrom(EditText::class.java)).perform(
            typeText("zzzzzzzzzzzzzzzzzzzzz"),
            pressKey(KeyEvent.KEYCODE_ENTER)
        )
        onView(withId(R.id.tv_message)).check(ViewAssertions.matches(isDisplayed()))
    }
}