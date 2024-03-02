package com.example.tsukeysmobile.Requests.Interface

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface CheckAuthInterface {
    @Headers("Content-Type: application/json")
    @GET("user/getProfile")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<Void>
}