package com.ubn.musicbrainz_place.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ubn.musicbrainz_place.model.MusicPlace
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicBrainzViewModel(val repository : MusicBrainzRepository) :ViewModel(){


    var placees = MutableLiveData<Result<List<MusicPlace>>>()

    init {
        viewModelScope.launch {
            repository.getMusikPlace()
        }

    }
}