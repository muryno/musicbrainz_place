package com.ubn.musicbrainz_place.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class LifeSpan : Serializable {
    @SerializedName("ended")
    @Expose
    var ended: Any? = null

    companion object {
        private const val serialVersionUID = -4670251122918910290L
    }
}