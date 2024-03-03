package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.KeyRequests.KeyRequests
import com.example.tsukeysmobile.Requests.Keys.ReservKey
import com.example.tsukeysmobile.Requests.Requests.RequestDataItem
import com.example.tsukeysmobile.Requests.Requests.Requests
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RequestsInterface {
    @Headers("Content-Type: application/json")
    @GET("request/getMyRequests")
    fun getRequests(@Header("Authorization") token: String): Call<Requests>

    @Headers("Content-Type: application/json")
    @DELETE("request/delete")
    fun deleteRequest(@Header("Authorization") token: String,
                      @Query ("RequestId") requestId: String): Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("key/confirm/dean/{request}")
    fun confirmKeyRequest(@Header("Authorization") token: String, @Path("request") requestId: String): Call<Void>

    @Headers("Content-Type: application/json")
    @GET("key/requests")
    fun getKeyRequests(@Header("Authorization") token: String,
                       @Query ("userStatus") userStatus: String): Call<KeyRequests>

    @Headers("Content-Type: application/json")
    @DELETE("key/delete/request")
    fun deleteKeyRequest(@Header("Authorization") token: String,
                       @Query ("request") requestId: String): Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("key/accept/request")
    fun acceptKeyRequest(@Header("Authorization") token: String,
                         @Query ("id") requestId: String): Call<Void>
    @Headers("Content-Type: application/json")
    @PUT("key/confirm/getting")
    fun confirmKeyRequestFromPeople(@Header("Authorization") token: String,
                         @Query ("request") requestId: String): Call<Void>

    @Headers("Content-Type: application/json")
    @PUT("key/request")
    fun createKeyRequest(@Header("Authorization") token: String,
                         @Body keyRecipient: String,
                         @Body classroomNumber: String): Call<Void>
}