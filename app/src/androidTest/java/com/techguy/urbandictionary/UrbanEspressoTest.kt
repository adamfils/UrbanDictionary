package com.techguy.urbandictionary

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.techguy.urbandictionary.view.MainActivity
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class UrbanEspressoTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkSearchBar() {
        //Check for the word Baby in Search Bar
        onView(withText("lol"))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun enterSearchQuery() {
        //Clear EditText
        onView(withId(R.id.search_bar)).perform(clearText())
        //Tap On The Filter Button
        onView(withId(R.id.search_bar)).perform(typeText("hello"))
    }

    @Test
    fun searchQuery() {
        //Tap On The Filter Button
        onView(withId(R.id.search_bar)).perform(ViewActions.pressImeActionButton())
    }

    @Test
    fun clickFilterButton() {
        //Tap On The Filter Button
        onView(withId(R.id.fab)).perform(ViewActions.click())
    }

    @Test
    fun checkIfSearchEmpty(){
        //Check That Search Bar Is Not Empty
        onView(withId(R.id.search_bar)).check(matches(not(withText(""))));

    }
}