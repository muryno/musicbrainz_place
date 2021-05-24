package com.ubn.musicbrainz_place

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.schibsted.spain.barista.assertion.BaristaHintAssertions.assertHint
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class MusicBrainzFeatures {

    //getting the activity we want to test
    val mActivityRule = ActivityTestRule(MainActivity::class.java)
    @Rule get

    @Test
    fun displayScreenTitle() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ubn.musicbrainz_place", appContext.packageName)
    }


    @Test
    fun displayHintEditText(){
        assertHint(R.id.editTextCountryName, R.string.enter_country);
       // assertDisplayed(assertHint(R.id.edittext, "Hint");)
    }
}