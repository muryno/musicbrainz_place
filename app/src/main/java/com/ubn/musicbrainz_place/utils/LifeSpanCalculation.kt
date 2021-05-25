package com.ubn.musicbrainz_place.utils

import javax.inject.Inject

//Single responsibility principle
class LifeSpanCalculation @Inject constructor() {



    //life span calculatione
     fun getLifeSpan(begin : String?) : Int{
        var span = -1

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