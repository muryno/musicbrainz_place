package com.ubn.musicbrainz_place.utils

import android.annotation.TargetApi
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.ubn.musicbrainz_place.MusicBrainApplication

class CheckInternetConnection {


    companion object{
        @TargetApi(Build.VERSION_CODES.M)
        fun isOnline(): Boolean {

            if ( MusicBrainApplication.instance?.applicationContext == null)
                return false

            val cm = MusicBrainApplication.instance?.applicationContext
                ?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetwork
            return activeNetwork != null
        }
    }



}