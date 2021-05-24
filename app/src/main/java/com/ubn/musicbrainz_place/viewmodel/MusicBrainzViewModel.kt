package com.ubn.musicbrainz_place.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import javax.inject.Inject

class MusicBrainzViewModel @Inject constructor(val repository : MusicBrainzRepository) :ViewModel(){



  fun  getMusicCountryPlace( country : String): LiveData<Result<MusicPlace>> {
      return liveData {
        emitSource(repository.getMusikPlace(country).asLiveData())
    }

  }


}