package com.ubn.musicbrainz_place.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Area : Serializable {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("type-id")
    @Expose
    var typeId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("sort-name")
    @Expose
    var sortName: String? = null

    @SerializedName("life-span")
    @Expose
    var lifeSpan: LifeSpan? = null

    companion object {
        private const val serialVersionUID = -3855745713085353954L
    }
}