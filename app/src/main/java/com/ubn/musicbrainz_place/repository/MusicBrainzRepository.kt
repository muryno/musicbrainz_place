package com.ubn.musicbrainz_place.repository

import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.services.MusikBrainzService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MusicBrainzRepository  @Inject constructor (var service : MusikBrainzService){
   suspend fun getMusikPlace( country : String): Flow<Result<MusicPlace>> {

      return service.getMusixPlaceService(country)
    }

}
