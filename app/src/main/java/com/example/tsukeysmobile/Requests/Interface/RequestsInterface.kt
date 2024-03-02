package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Requests.RequestDataItem
import com.example.tsukeysmobile.Requests.Requests.Requests
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface RequestsInterface {
    @Headers("Content-Type: application/json")
    @GET("request/getMyRequests")
    fun getRequests(@Header("Authorization") token: String): Call<Requests>
}