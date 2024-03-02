package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Requests.RequestDataItem
import com.example.tsukeysmobile.Requests.Requests.Requests
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface RequestsInterface {
    @Headers("Content-Type: application/json")
    @GET("request/getMyRequests")
    fun getRequests(@Header("Authorization") token: String): Call<Requests>

    @Headers("Content-Type: application/json")
    @DELETE("request/delete")
    fun deleteRequest(@Header("Authorization") token: String,
                      @Query ("RequestId") requestId: String): Call<Void>
}