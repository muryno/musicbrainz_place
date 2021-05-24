package com.ubn.musicbrainz_place

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.server.MusicPlaceApi
import com.ubn.musicbrainz_place.services.MusikBrainzService
import com.ubn.musicbrainz_place.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class MusicBrainzServiceTest : BaseUnitTest() {


    lateinit var service : MusikBrainzService
    var api : MusicPlaceApi = mock()
    var musicPlaceList : MusicPlace = mock()


    @Test
    fun  fetchMusicPlaceFromApi() = runBlockingTest{

         service = MusikBrainzService(api)
        service.getMusixPlaceService("Lithumania");
        verify(api, times(1)).fetchMusicCountries("Lithumania")

    }


    @Test
    fun  convertValueToResultAndEmit() = runBlockingTest {
        whenever(api.fetchMusicCountries("Lithumania")).thenReturn(musicPlaceList)
        service = MusikBrainzService(api)

        Assert.assertEquals(Result.success(musicPlaceList), service.getMusixPlaceService("Lithumania").first())
    }


    @Test
    fun emitFailureError() = runBlockingTest{

//
//        whenever(api.fetchMusicCountries()).thenThrow(RuntimeException("something went wrong"))
//
//
//        service = MusikBrainzService(api)
//
//        Assert.assertEquals(
//            "something went wrong",
//            service.getMusixPlaceService("Lithuania").first()?.exceptionOrNull()?.message
//        )
    }

}