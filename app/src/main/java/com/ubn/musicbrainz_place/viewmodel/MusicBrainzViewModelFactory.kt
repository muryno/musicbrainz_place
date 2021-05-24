package com.ubn.musicbrainz_place.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository

class MusicBrainzViewModelFactory(val repository : MusicBrainzRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  MusicBrainzViewModel(repository) as T
    }
}