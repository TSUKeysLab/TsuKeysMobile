package com.example.tsukeysmobile.Requests

import android.util.Log
import com.example.tsukeysmobile.AUTHORIZE_TOKEN
import com.example.tsukeysmobile.Requests.Interface.KeysInterface
import com.example.tsukeysmobile.Requests.Interface.UserInterface
import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import com.example.tsukeysmobile.Requests.Keys.ReservKey
import com.example.tsukeysmobile.Requests.Registration.AuthTokenDataItem
import com.example.tsukeysmobile.Requests.Registration.RegistrationDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val BASE_URL = "http://89.111.174.112:8181/"
private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

class RequestsFunctions {

    suspend fun getKeys(
        year: Int,
        month: Int,
        day: Int,
        timeId: Int
    ): List<KeysDataItem> {
        return suspendCoroutine { continuation ->
            val keysInterface = retrofit.create(KeysInterface::class.java)
            val retrofitData =
                keysInterface.getKeys(AUTHORIZE_TOKEN, year, month, day, timeId, "AvailableKeys")

            retrofitData.enqueue(object : Callback<List<KeysDataItem>> {
                override fun onResponse(
                    call: Call<List<KeysDataItem>>,
                    response: Response<List<KeysDataItem>>
                ) {
                    if (response.isSuccessful) {
                        val responseBody = response.body()
                        val keys = responseBody ?: emptyList()
                        Log.d("Res", keys.size.toString())
                        continuation.resume(keys)
                    } else {
                        Log.d("Fail", "Unsuccessful response: ${response.code()}")
                        continuation.resume(emptyList())
                    }
                }

                override fun onFailure(call: Call<List<KeysDataItem>>, t: Throwable) {
                    Log.d("Fail", t.message!!)
                    continuation.resume(emptyList())
                }
            })
        }
    }

    suspend fun reservationCab(
        date: String,
        les: Int,
        cab: String
    ): Int {
        return suspendCoroutine { continuation ->
            val keysInterface = retrofit.create(KeysInterface::class.java)
            val requestBody = ReservKey(cab, les, date)
            val retrofitData = keysInterface.postReservation(AUTHORIZE_TOKEN, requestBody)
            retrofitData.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        Log.d("Cool", "All right!: ${response.code()}")
                        continuation.resume(response.code())
                    } else {
                        Log.d("Bad", "All bad!: ${response.code()}")
                        continuation.resume(response.code())
                    }

                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {
                    Log.d("Bad", "All bad!: ${t.message}")
                }
            })

        }
    }

    suspend fun postRegistration(
        name: String,
        surname: String,
        bd: String,
        gender: String,
        email: String,
        password: String
    ): Response<AuthTokenDataItem> {
        return suspendCoroutine { continuation ->
            val regInterface = retrofit.create(UserInterface::class.java)
            val requestBody = RegistrationDataItem(name, surname, bd, gender, email, password)
            val retrofitData = regInterface.postUserRegistration(requestBody)

            retrofitData.enqueue(object : Callback<AuthTokenDataItem> {
                override fun onResponse(
                    call: Call<AuthTokenDataItem>,
                    response: Response<AuthTokenDataItem>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Cool", "All right!: ${response.code()}")
                        val token = response.body()?.token
                        AUTHORIZE_TOKEN = "Bearer $token"
                        Log.d("Auf", AUTHORIZE_TOKEN)
                        continuation.resume(response)
                    } else {
                        Log.d("Bad", "All bad!: ${response.code()}")
                        continuation.resume(response)
                    }
                }

                override fun onFailure(call: Call<AuthTokenDataItem>, t: Throwable) {
                    Log.d("Bad", "All bad!: ${t.message}")
                }
            })
        }
    }
    suspend fun checkUserAuth(): Int{
        return suspendCoroutine { continuation ->
            val authInterface = retrofit.create(UserInterface::class.java)

            val retrofitData = authInterface.getProfile(AUTHORIZE_TOKEN)
            retrofitData.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        Log.d("Cool", "All right!: ${response.code()}")
                        continuation.resume(response.code())
                    } else {
                        Log.d("Bad", "All bad!: ${response.code()}")
                        continuation.resume(response.code())
                    }

                }

                override fun onFailure(call: Call<Void?>, t: Throwable) {
                    Log.d("Bad", "All bad!: ${t.message}")
                }
            })

        }
    }


}