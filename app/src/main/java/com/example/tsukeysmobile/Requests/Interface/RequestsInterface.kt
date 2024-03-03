package com.example.tsukeysmobile.Requests.Interface

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
}