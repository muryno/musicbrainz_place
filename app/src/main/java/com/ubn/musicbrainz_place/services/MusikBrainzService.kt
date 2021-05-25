package com.ubn.musicbrainz_place.services

import com.ubn.musicbrainz_place.config.MusicPlaceApi
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.utils.FilterMusicPlace
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.RuntimeException
import javax.inject.Inject

class MusikBrainzService   @Inject constructor(var musicApi : MusicPlaceApi,var filterMusic: FilterMusicPlace?) {
  suspend  fun getMusixPlaceService(country : String): Flow<Result<MusicPlace>> {


    val result =   musicApi.fetchMusicCountries(country)
        return flow{

            //if successful, filter places below 1990



                val  filteredPlaces =   filterMusic?.filterMusicPlace(result.places)

                //initialize the array to empty and adding new places


                result.places = ArrayList()
                result.places = filteredPlaces



            emit(Result.success(result))
        }.catch {
           emit( Result.failure(RuntimeException(it.message)))
        }
    }

}
