package com.ubn.musicbrainz_place.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LifeSpan__1 : Serializable {
    @SerializedName("begin")
    @Expose
    var begin: String? = null

    @SerializedName("ended")
    @Expose
    var ended: Any? = null

    @SerializedName("end")
    @Expose
    var end: String? = null

    companion object {
        private const val serialVersionUID = -1270633315262597835L
    }
}