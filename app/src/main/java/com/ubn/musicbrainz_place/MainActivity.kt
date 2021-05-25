package com.ubn.musicbrainz_place

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ubn.musicbrainz_place.model.Place
import com.ubn.musicbrainz_place.viewmodel.MusicBrainzViewModel
import com.ubn.musicbrainz_place.viewmodel.MusicBrainzViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Hilt
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    lateinit var viewModel : MusicBrainzViewModel
    lateinit var placeLatLon  : List<Place>
   lateinit var mgoogleMap: GoogleMap
    @Inject
    lateinit var viewModelFactory: MusicBrainzViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        viewModel = ViewModelProvider(this,viewModelFactory).get(MusicBrainzViewModel::class.java)


        placeLatLon = ArrayList()

        val editable = findViewById<EditText>(R.id.editTextCountryName)
        val submitButton = findViewById<Button>(R.id.submitButton)


        submitButton.setOnClickListener {
            viewModel.getMusicCountryPlace(editable.text.toString()).observe(this as LifecycleOwner,
                {

                    if (it.isSuccess){


                        it.onSuccess { myPlace ->
                            placeLatLon =   myPlace.places!!.toList()
                            onMapReady(mgoogleMap)
                        }


                       // print(it.getOrNull())
                    }

                })
        }



    }

    override fun onMapReady(googleMap: GoogleMap,) {

        mgoogleMap = googleMap
        if (placeLatLon.size > 0) {
            placeLatLon.forEach { place ->
                val marker = googleMap.addMarker(
                    MarkerOptions()
                        .title(place.name)
                        .position(
                            LatLng(
                                place.coordinates?.latitude?.toDouble() ?: 0.0,
                                place.coordinates?.longitude?.toDouble() ?: 0.0
                            )
                        )
                )
                googleMap.moveCamera(CameraUpdateFactory.newLatLng( LatLng(
                    place.coordinates?.latitude?.toDouble() ?: 0.0,
                    place.coordinates?.longitude?.toDouble() ?: 0.0
                )))
                marker?.tag = place.name
            }
        }
    }
}