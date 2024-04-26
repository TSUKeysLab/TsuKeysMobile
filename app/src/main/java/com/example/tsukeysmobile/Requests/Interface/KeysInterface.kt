package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.KeyRequests.KeyOwnedData
import com.example.tsukeysmobile.Requests.KeyRequests.KeyRecipientUsersData
import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import com.example.tsukeysmobile.Requests.Keys.ReservKey
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface KeysInterface {
    @Headers("Content-Type: application/json")
    @GET("key/available")
    fun getKeys(@Header("Authorization") token: String,
                @Query("year") year: Int,
                @Query("month") month: Int,
                @Query("day") day: Int,
                @Query("timeId") timeId: Int): Call<List<KeysDataItem>>

    @Headers("Content-Type: application/json")
    @POST("request/create")
    fun postReservation(
        @Header("Authorization") token: String,
        @Body request: ReservKey
    ): Call<Void>

    @Headers("Content-Type: application/json")
    @GET("key/owned")
    fun getOwnedKeys(@Header("Authorization") token: String): Call<KeyOwnedData>

    @Headers("Content-Type: application/json")
    @GET("key/request/users")
    fun getRecipientsUsers(@Header("Authorization") token: String,
                           @Query("fullname") fullname: String): Call<KeyRecipientUsersData>
}