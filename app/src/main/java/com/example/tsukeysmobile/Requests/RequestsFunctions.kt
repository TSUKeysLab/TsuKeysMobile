package com.example.tsukeysmobile.Requests

import android.util.Log
import com.example.tsukeysmobile.Requests.Interface.KeysInterface
import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import com.example.tsukeysmobile.Requests.Keys.ReservKey
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val AUTHORIZE_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InVzZXJAZXhhbXBsZS5jb20iLCJuYmYiOjE3MDk0MDUzNDcsImV4cCI6MTcwOTQwNjI0NywiaWF0IjoxNzA5NDA1MzQ3LCJpc3MiOiJKV1RUb2tlbiIsImF1ZCI6Ikh1bWFuIn0.qgvjPeGajl2le6JU0eFRWIoTbkeI1MtQiRmnl07Samg"
const val BASE_URL = "http://89.111.174.112:8181/"
private val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

class RequestsFunctions {

    suspend fun getKeys(year: Int, month: Int, day: Int, timeId: Int): List<KeysDataItem> {
        return suspendCoroutine { continuation ->
            val keysInterface = retrofit.create(KeysInterface::class.java)
            val retrofitData = keysInterface.getKeys(AUTHORIZE_TOKEN, year, month, day, timeId, "AvailableKeys")

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

    suspend fun reservationCab(date: String, les: Int, cab: String): Int{
        return suspendCoroutine { continuation ->
            val keysInterface = retrofit.create(KeysInterface::class.java)
            val requestBody = ReservKey(cab, les, date)
            val retrofitData = keysInterface.postReservation(AUTHORIZE_TOKEN, requestBody)
            retrofitData.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if (response.isSuccessful) {
                        Log.d("Cool", "All right!: ${response.code()}")
                        continuation.resume(response.code())
                    }
                    else{
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