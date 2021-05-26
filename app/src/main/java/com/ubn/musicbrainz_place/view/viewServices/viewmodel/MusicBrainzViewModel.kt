package com.ubn.musicbrainz_place.view.viewServices.viewmodel

import android.os.CountDownTimer
import androidx.lifecycle.*
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.model.Place
import com.ubn.musicbrainz_place.view.viewServices.repository.MusicBrainzRepository
import javax.inject.Inject

class MusicBrainzViewModel @Inject constructor(val repository : MusicBrainzRepository) :ViewModel(){



    var message = MutableLiveData<String>()

    ///pleacese left after filter
    var placesLeft =   MutableLiveData< ArrayList<Place>>();



    ///make network request
    fun  getMusicCountryPlace( country : String): LiveData<Result<MusicPlace>> {
        return liveData {
            emitSource(repository.getMusikPlace(country).asLiveData())
        }

    }



    ///refresh counter
    fun refreshCountDown( place : ArrayList<Place>) {

        place.forEach {

            if (it.expiredSec > 0) {
                val timer = object : CountDownTimer(it.expiredSec.toLong() * 1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {

                        message.value ="Removed ${it.name.toString()}"
                        place.remove(it)
                        placesLeft.value = place
                    }
                }
                timer.start()

            }
        }

    }



}