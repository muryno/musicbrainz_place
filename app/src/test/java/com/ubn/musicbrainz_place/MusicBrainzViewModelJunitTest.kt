package com.ubn.musicbrainz_place

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
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


class MusicBrainzViewModelJunitTest {


    @get:Rule
    var couroutineTestRule =  MainCoroutineScopeRule()


    ///InstantTaskExecutorRule allow execution of live data to happen instantly
    @get:Rule
    var instantTaskExecutorRule  = InstantTaskExecutorRule()



  private  var viewModel : MusicBrainzViewModel

    private  var repository : MusicBrainzRepository = mock()

    private  val placeMarker = mock<List<MusicPlace>>()

    private  val expected = Result.success(placeMarker)
    init {

        runBlocking {
            ///mock music place playlist
            whenever(repository.getMusikPlace()).thenReturn(

                flow{
                    emit(expected)
                }
            )
        }



        viewModel = MusicBrainzViewModel(repository)

    }

    @Test
    fun getPlacesFromRepository() = runBlockingTest{
        ///getValueForTest is a generic class to get live data data
      viewModel.placees.getValueForTest()

        verify(repository,times(1)).getMusikPlace()
    }

    @Test
    fun emmitMusicPlaceFromRepository()= runBlockingTest{

        assertEquals(expected, viewModel.placees.getValueForTest())
    }
}