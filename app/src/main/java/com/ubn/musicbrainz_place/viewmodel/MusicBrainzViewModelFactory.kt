package com.ubn.musicbrainz_place.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import javax.inject.Inject

class MusicBrainzViewModelFactory @Inject constructor(val repository : MusicBrainzRepository) : ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return  MusicBrainzViewModel(repository) as T
    }
}