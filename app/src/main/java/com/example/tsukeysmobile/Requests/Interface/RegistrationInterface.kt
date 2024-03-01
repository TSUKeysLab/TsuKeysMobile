package com.example.tsukeysmobile.Requests.Interface

import com.example.tsukeysmobile.Requests.Registration.RegistrationDataItem
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface RegistrationInterface {
    @Headers("Content-Type: application/json")
    @POST("user/register")
    fun postUserRegistration(
        @Body request: RegistrationDataItem
    ): Call<String>
}