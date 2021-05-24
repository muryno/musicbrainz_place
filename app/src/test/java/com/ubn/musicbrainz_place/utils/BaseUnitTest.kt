package com.ubn.musicbrainz_place.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {



    @get:Rule
    var couroutineTestRule =  MainCoroutineScopeRule()


    ///InstantTaskExecutorRule allow execution of live data to happen instantly
    @get:Rule
    var instantTaskExecutorRule  = InstantTaskExecutorRule()
}