package com.ubn.musicbrainz_place.services

import com.ubn.musicbrainz_place.server.MusicPlaceApi
import com.ubn.musicbrainz_place.model.MusicPlace
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

class MusikBrainzService   @Inject constructor(var musicApi : MusicPlaceApi) {
  suspend  fun getMusixPlaceService(country : String): Flow<Result<MusicPlace>> {

    val result =   musicApi.fetchMusicCountries(country)
        return flow{
            emit(Result.success(result))
        }.catch {
           emit( Result.failure(RuntimeException("something went wrong")))
        }
    }

}
