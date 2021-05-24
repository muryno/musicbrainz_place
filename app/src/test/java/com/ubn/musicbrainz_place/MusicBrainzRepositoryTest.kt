package com.ubn.musicbrainz_place

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import com.ubn.musicbrainz_place.services.MusikBrainzService
import com.ubn.musicbrainz_place.utils.BaseUnitTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.internal.verification.VerificationModeFactory.times

class MusicBrainzRepositoryTest: BaseUnitTest() {

  private val service : MusikBrainzService = mock()


    private   val  failureException = RuntimeException("Something went wrong")


    private  val mockPlayList  = mock<MusicPlace>()
    @Test
     fun  getMusicPlaceFromService() = runBlockingTest{

        val repository = MusicBrainzRepository(service)
        repository.getMusikPlace("Lithumia")
        verify(service,times(1)).getMusixPlaceService("Lithumia")

    }


    private fun mockSuccessfulMessage( country : String) : MusicBrainzRepository {
        runBlocking {
            ///mock music place playlist
            whenever(service.getMusixPlaceService(country)).thenReturn(

                flow{
                    emit(Result.success(mockPlayList))
                }
            )

        }
        val playlistRepository =  MusicBrainzRepository(service)
        return  playlistRepository
    }


    @Test
      fun emitMusicPlaceFromService() = runBlockingTest {
            val playlistRepository =  mockSuccessfulMessage("Lithuania")

            Assert.assertEquals(
                mockPlayList,
                playlistRepository.getMusikPlace("Lithuania").first().getOrNull()
            )

        }



    @Test
    fun emitFailureError() = runBlockingTest{
        val repository =  MusicBrainzRepository(service)
        runBlocking{
            whenever(repository.getMusikPlace("Lithuania")).thenReturn(
                flow {
                    emit(Result.failure<MusicPlace>(failureException))
                }
            )
        }



        Assert.assertEquals(
            failureException,
            repository.getMusikPlace("Lithuania").first().exceptionOrNull()
        )
    }




}