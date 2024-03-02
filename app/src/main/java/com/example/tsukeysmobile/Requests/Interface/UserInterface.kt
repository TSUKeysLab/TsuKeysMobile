package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Registration.AuthTokenDataItem
import com.example.tsukeysmobile.Requests.Registration.RegistrationDataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserInterface {

    @Headers("Content-Type: application/json")
    @POST("user/register")
    fun postUserRegistration(
        @Body request: RegistrationDataItem
    ): Call<AuthTokenDataItem>


    @Headers("Content-Type: application/json")
    @GET("user/getProfile")
    fun getProfile(
        @Header("Authorization") token: String
    ): Call<Void>

    @Headers("Content-Type: application/json")
    @POST("user/register")
    fun postUserAuthentication(
        @Body request: AuthTokenDataItem
    ): Call<AuthTokenDataItem>

}