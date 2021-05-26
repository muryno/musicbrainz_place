package com.ubn.musicbrainz_place.view

import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ubn.musicbrainz_place.R
import com.ubn.musicbrainz_place.model.Place
import com.ubn.musicbrainz_place.utils.CheckInternetConnection
import com.ubn.musicbrainz_place.view.viewServices.baseActivity.BaseActivity
import com.ubn.musicbrainz_place.view.viewServices.viewmodel.MusicBrainzViewModel
import com.ubn.musicbrainz_place.view.viewServices.viewmodel.MusicBrainzViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


//Hilt
@AndroidEntryPoint
class MainActivity : BaseActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: MusicBrainzViewModelFactory


    lateinit var viewModel : MusicBrainzViewModel
    lateinit var placeLatLon  : MutableList<Place>
    lateinit var mgoogleMap: GoogleMap



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //map initialize
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        //view model
        viewModel = ViewModelProvider(this,viewModelFactory).get(MusicBrainzViewModel::class.java)


        //initialize array
        placeLatLon = ArrayList()


      //  submitButton?.isEnabled = CheckInternetConnection.isOnline()

        //I used  kotlin-android-extensions for auto binding,
        //  data binding is advisable for big project
        submitButton.setOnClickListener { it ->

            if(editTextCountryName.text.isNotEmpty()) {
                val progrss =progressDialog("fetching music places in ${editTextCountryName.text}")
                //showKeyboard
                it.hideKeyboard()
                //show progress dialog
                progrss?.show()
                viewModel.getMusicCountryPlace(editTextCountryName.text.toString()).observe(this as LifecycleOwner,
                    {resp-> if (resp.isSuccess) {
                        // date less than 1990 has been filtered from  MusikBrainzRepository Class
                        resp.onSuccess { myPlace ->
                            if (myPlace.places?.isNotEmpty() == true) {
                                placeLatLon = myPlace.places?.toList() as MutableList<Place>
                                mgoogleMap.clear()
                                onMapReady(mgoogleMap)
                                //call reset function
                                viewModel.refreshCountDown(placeLatLon as ArrayList<Place>)
                                progrss?.hide()
                            } else {
                                toastMessage( "please enter country you ant to search")
                                progrss?.hide()
                            }
                        }
                    }else if(resp.isFailure){

                        resp.onFailure {
                            toastMessage( it.message.toString())
                        }
                    }
                    }
                )
            }else{
                toastMessage( "No Music place available")
            }
        }


        //view model
        viewModel.placesLeft.observe(this as LifecycleOwner, Observer {
            if(it.size >= 0){
                //initializing the places and adding to placeLatLon
                placeLatLon= ArrayList()
                placeLatLon = it
                mgoogleMap.clear()
                onMapReady(mgoogleMap)
            }
        })

        // view model
        viewModel.message.observe(this as LifecycleOwner, {
            if(it.isNotEmpty()){
                toastMessage(it)
            }
        })
    }
    override fun onMapReady(googleMap: GoogleMap) {
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