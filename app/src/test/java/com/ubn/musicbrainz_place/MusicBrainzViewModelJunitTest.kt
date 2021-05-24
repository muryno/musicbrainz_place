package com.ubn.musicbrainz_place

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import com.ubn.musicbrainz_place.utils.BaseUnitTest
import com.ubn.musicbrainz_place.utils.MainCoroutineScopeRule
import com.ubn.musicbrainz_place.utils.getValueForTest
import com.ubn.musicbrainz_place.viewmodel.MusicBrainzViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.Mock
import java.lang.RuntimeException


class MusicBrainzViewModelJunitTest: BaseUnitTest() {




  //private  var viewModel : MusicBrainzViewModel

    private  var repository : MusicBrainzRepository = mock()

    private  val placeMarker = mock<List<MusicPlace>>()

    private  val expected = Result.success(placeMarker)

  private   val  failureException = RuntimeException("Something went wrong")

    private fun mockSuccessfulMessage( country : String) : MusicBrainzViewModel{
        runBlocking {
            ///mock music place playlist
            whenever(repository.getMusikPlace(country)).thenReturn(

                flow{
                    emit(expected)
                }
            )
        }
        val  viewModel = MusicBrainzViewModel(repository)
        return  viewModel
    }

    @Test
     fun emitFailureError() = runBlockingTest{

        runBlocking{
            whenever(repository.getMusikPlace("Lithuania")).thenReturn(
                flow {
                    emit(Result.failure<List<MusicPlace>>(failureException))
                }
            )
        }

        val  viewmodel = MusicBrainzViewModel(repository)

        assertEquals(failureException , viewmodel.getMusicCountryPlace("Lithuania").getValueForTest()?.exceptionOrNull())
    }


    @Test
    fun getPlacesFromRepository() = runBlockingTest{
        ///getValueForTest is a generic class to get live data data


     val   viewModel =mockSuccessfulMessage("Lithuania")

        viewModel.getMusicCountryPlace("Lithuania").getValueForTest()

        verify(repository,times(1)).getMusikPlace("Lithuania")
    }

    @Test
    fun emmitMusicPlaceFromRepository()= runBlockingTest{
        val   viewModel =mockSuccessfulMessage("Lithuania")
        assertEquals(expected, viewModel.getMusicCountryPlace("Lithuania").getValueForTest())
    }
}