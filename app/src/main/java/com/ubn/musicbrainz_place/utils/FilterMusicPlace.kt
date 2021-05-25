package com.ubn.musicbrainz_place.utils

import com.ubn.musicbrainz_place.model.Place
import kotlinx.coroutines.*
import javax.inject.Inject

class FilterMusicPlace @Inject constructor(var lifeSpanCalculation: LifeSpanCalculation) {


    /// filter places below 1990
    suspend fun filterMusicPlace(  places: List<Place>?): List<Place>{

      val  placesFiltered  : MutableList<Place> = ArrayList()

        withContext(Dispatchers.Default) {
        places?.forEach {

            val result = lifeSpanCalculation.getLifeSpan(it.lifeSpan?.begin )

            if (result >= 0) {
                ///I am extracting the second and adding to expiring second
              val plase : Place  = it
                plase.expiredSec = result
                placesFiltered.add(plase)
            }
        }
        }
        return  placesFiltered

    }

}