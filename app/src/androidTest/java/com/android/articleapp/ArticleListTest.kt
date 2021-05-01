package com.android.articleapp

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.android.articleapp.view.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleListTest {
    @Rule //Activity Test Rule for the activity which contains my listing fragment
    var mActivityRule = ActivityTestRule(
        MainActivity::class.java
    )

    @Test
    @Throws(InterruptedException::class)
    fun onRecyclerViewClick() {
        //Perform type action on the view with id
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(ViewActions.click())
    }

    @Test
    fun onSearchClick() {
        Espresso.onView(ViewMatchers.withId(R.id.action_search)).perform(ViewActions.click())
    }
}