package com.ubn.musicbrainz_place.server

import com.ubn.musicbrainz_place.BuildConfig
import com.ubn.musicbrainz_place.model.MusicPlace
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MusicPlaceApi {


   @Headers(
      "Accept: application/json",
      "User-Agent: ${BuildConfig.APPLICATION_ID}/${BuildConfig.VERSION_NAME}"
   )
   @GET("place/")
   suspend fun fetchMusicCountries(@Query("query") userId: String): MusicPlace
}
