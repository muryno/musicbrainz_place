package com.ubn.musicbrainz_place

import androidx.test.espresso.action.ViewActions.*

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint
import com.ubn.musicbrainz_place.view.MainActivity
import org.junit.Assert.assertEquals


@RunWith(AndroidJUnit4::class)
class MusicBrainzFeatures {







    private lateinit var stringToBetyped: String
//

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBetyped = "United state"
    }

    @Test
    fun displayScreenTitle() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ubn.musicbrainz_place", appContext.packageName)
    }


    @Test
    fun displayHintEditText(){
        assertHint(R.id.editTextCountryName, R.string.enter_country);
    }


    @Test
    fun acceptanceTestForEditTextAndButtonClicked() {
        // Type text and then press the button.
        onView(withId(R.id.editTextCountryName))

                .perform(typeText(stringToBetyped), closeSoftKeyboard())
        onView(withId(R.id.submitButton)).perform(click())

    }

}