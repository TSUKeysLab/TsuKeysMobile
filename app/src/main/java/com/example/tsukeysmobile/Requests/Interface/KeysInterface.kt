package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface KeysInterface {
    @Headers("Content-Type: application/json")
    @GET("key")
    fun getKeys(@Header("Authorization") token: String,
                @Query("year") year: Int,
                @Query("month") month: Int,
                @Query("day") day: Int,
                @Query("timeId") timeId: Int,
                @Query("gettingStatus") gettingStatus: String): Call<List<KeysDataItem>>

}