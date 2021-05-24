package com.ubn.musicbrainz_place.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Place : Serializable {



    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("type-id")
    @Expose
    var typeId: String? = null

    @SerializedName("score")
    @Expose
    var score: Int? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("address")
    @Expose
    var address: String? = null

    @SerializedName("coordinates")
    @Expose
    var coordinates: Coordinates? = null

    @SerializedName("area")
    @Expose
    var area: Area? = null

    @SerializedName("life-span")
    @Expose
    var lifeSpan: LifeSpan__1? = null

    @SerializedName("aliases")
    @Expose
    var aliases: List<Alias>? = null

    @SerializedName("disambiguation")
    @Expose
    var disambiguation: String? = null

    constructor()

    companion object {
        private const val serialVersionUID = -3457303393196143072L
    }
}