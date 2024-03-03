package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Profile.ProfileData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface ProfileInterface {
    @Headers("Content-Type: application/json")
    @GET("user/getProfile")
    fun getProfile(@Header("Authorization") token: String): Call<ProfileData>
}