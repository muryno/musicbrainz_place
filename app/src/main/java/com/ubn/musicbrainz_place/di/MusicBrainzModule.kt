package com.ubn.musicbrainz_place.di

import com.ubn.musicbrainz_place.config.Configs.Companion.BASE_URL
import com.ubn.musicbrainz_place.config.Configs.Companion.client
import com.ubn.musicbrainz_place.config.MusicPlaceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
@InstallIn(ActivityComponent::class)
class MusicBrainzModule {



    @Provides
    fun musicPlaceApi(retrofit : Retrofit) = retrofit.create(MusicPlaceApi::class.java)



    @Provides
    fun retrofit() = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .client(client)
    .addConverterFactory(
    GsonConverterFactory.create())
    .build()
}