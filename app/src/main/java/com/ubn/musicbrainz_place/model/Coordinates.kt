package com.ubn.musicbrainz_place.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable



class Coordinates : Serializable {
    @SerializedName("latitude")
    @Expose
    var latitude: String? = null

    @SerializedName("longitude")
    @Expose
    var longitude: String? = null

    companion object {
        private const val serialVersionUID = 9173136842460698557L
    }
}