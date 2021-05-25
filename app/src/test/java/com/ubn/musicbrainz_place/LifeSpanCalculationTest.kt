package com.ubn.musicbrainz_place

import com.ubn.musicbrainz_place.utils.BaseUnitTest
import com.ubn.musicbrainz_place.utils.LifeSpanCalculation
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class LifeSpanCalculationTest: BaseUnitTest() {


    var lifeSpan = LifeSpanCalculation()

    @Test
    fun testLifeSpanAbove1990()= runBlockingTest {

      val result =   lifeSpan.getLifeSpan("1993-09-18")

        Assert.assertEquals(3,result)

    }


    @Test
    fun testLifeSpanBelow1990()= runBlockingTest {

        val result =   lifeSpan.getLifeSpan("1913-09-18")

        Assert.assertEquals(-77,result)

    }
}