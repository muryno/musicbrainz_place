package com.ubn.musicbrainz_place

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MusicBrainApplication : Application() {



    override fun onCreate() { super.onCreate()
        instance = this
    }

    //global application context
    companion object {
        var instance: MusicBrainApplication? = null
            private set

    }
}