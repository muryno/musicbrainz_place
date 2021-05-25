package com.ubn.musicbrainz_place.view

import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.ubn.musicbrainz_place.R
import com.ubn.musicbrainz_place.model.Place
import com.ubn.musicbrainz_place.utils.ProgressDialog
import com.ubn.musicbrainz_place.view.viewServices.baseActivity.BaseActivity
import com.ubn.musicbrainz_place.view.viewServices.viewmodel.MusicBrainzViewModel
import com.ubn.musicbrainz_place.view.viewServices.viewmodel.MusicBrainzViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Hilt
@AndroidEntryPoint
class MainActivity : BaseActivity(), OnMapReadyCallback {

    var vlaur = "welcome"

    lateinit var viewModel : MusicBrainzViewModel
    lateinit var placeLatLon  : MutableList<Place>
   lateinit var mgoogleMap: GoogleMap

    @Inject
    lateinit var viewModelFactory: MusicBrainzViewModelFactory

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

        val editable = findViewById<EditText>(R.id.editTextCountryName)
        val submitButton = findViewById<Button>(R.id.submitButton)


        submitButton.setOnClickListener {

            if(editable.text.isNotEmpty()) {

                val progrss =progressDialog("fetching music places in ${editable.text}")

                //shideKeyboard
                it.hideKeyboard()

                //show progress dialog
                progrss?.show()
                viewModel.getMusicCountryPlace(editable.text.toString()).observe(this as LifecycleOwner,
                        {

                            if (it.isSuccess) {


                                // date less than 1990 has been filtered from  MusikBrainzRepository Class
                                it.onSuccess { myPlace ->
                                    if (myPlace.places?.isNotEmpty() == true) {
                                        placeLatLon = myPlace.places?.toList() as MutableList<Place>
                                        mgoogleMap.clear()
                                        onMapReady(mgoogleMap)
                                        refreshCountDown(placeLatLon)
                                        progrss?.hide()
                                    } else {
                                        snackBar( "please enter country you ant to search")
                                        progrss?.hide()
                                    }
                                }


                                // print(it.getOrNull())
                            }

                        })
            }else{
                snackBar( "No Music place available")


            }
        }



    }

    override fun onMapReady(googleMap: GoogleMap) {

        Toast.makeText(this," $vlaur",Toast.LENGTH_SHORT).show()

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


    fun refreshCountDown( place : MutableList<Place>) {
        place.forEach {

            if (it.expiredSec > 0) {
                val timer = object : CountDownTimer(it.expiredSec.toLong() * 1000, 1000) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {

                        placeLatLon.remove(it)
                        vlaur ="Removed ${it.name.toString()}"
                        mgoogleMap.clear()
                        onMapReady(mgoogleMap)
                    }
                }
                timer.start()

            }
        }

    }

}