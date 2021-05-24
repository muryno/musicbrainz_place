package com.ubn.musicbrainz_place.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Alias : Serializable {
    @SerializedName("sort-name")
    @Expose
    var sortName: String? = null

    @SerializedName("type-id")
    @Expose
    var typeId: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("locale")
    @Expose
    var locale: Any? = null

    @SerializedName("type")
    @Expose
    var type: String? = null

    @SerializedName("primary")
    @Expose
    var primary: Any? = null

    @SerializedName("begin-date")
    @Expose
    var beginDate: String? = null

    @SerializedName("end-date")
    @Expose
    var endDate: String? = null


}