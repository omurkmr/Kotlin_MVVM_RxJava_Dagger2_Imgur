package com.omurkumru.imgurImages.ui.main

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.espresso.intent.rule.IntentsTestRule
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4
import com.omurkumru.imgurImages.R
import com.omurkumru.imgurImages.ui.about.AboutActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    val mActivityRule = IntentsTestRule(MainActivity::class.java)

    @Test
    fun openAboutActivity_clickInfoMenuItemTest() {
        onView(withId(R.id.action_info)).perform(click())
        intended(hasComponent(AboutActivity::class.java!!.name))
    }

    @Test
    fun imageList_scrollDownTest() {
        onView(withId(R.id.imgurImage_GridView)).perform(ViewActions.swipeUp())
        //TODO: Add some meaningful test here
    }

    @Test
    fun showViralSwitch_clickTest() {
        //checking possible exception
        onView(withId(R.id.viral_SW)).perform(click())
    }
}