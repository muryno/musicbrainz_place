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


        //calculates the lifespan based on the requirement "year - 1990"
        fun getLifeSpan() : Int{
            var span = 0

            try {
                begin?.let {
                    span = it.substring(0, 4).toInt() - 1990
                }
            }catch (e : Exception){
                print(e)
            }

            return span
        }

}