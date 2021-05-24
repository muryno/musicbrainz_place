package com.ubn.musicbrainz_place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ubn.musicbrainz_place.repository.MusicBrainzRepository
import com.ubn.musicbrainz_place.viewmodel.MusicBrainzViewModel
import com.ubn.musicbrainz_place.viewmodel.MusicBrainzViewModelFactory
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var viewModel : MusicBrainzViewModel
    lateinit var viewModelFactory: MusicBrainzViewModelFactory
    lateinit var repository :  MusicBrainzRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = MusicBrainzRepository()
        viewModelFactory = MusicBrainzViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(MusicBrainzViewModel::class.java)
    }
}