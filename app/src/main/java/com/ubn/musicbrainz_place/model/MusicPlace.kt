package com.ubn.musicbrainz_place.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class MusicPlace : Serializable {
    @SerializedName("created")
    @Expose
    var created: String? = null

    @SerializedName("count")
    @Expose
    var count: Int? = null

    @SerializedName("offset")
    @Expose
    var offset: Int? = null

    @SerializedName("places")
    @Expose
    var places: List<Place>? = null

    companion object {
        private const val serialVersionUID = 2623620937476020285L
    }
}