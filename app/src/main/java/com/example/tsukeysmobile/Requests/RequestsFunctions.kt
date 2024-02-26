package com.example.tsukeysmobile.Requests

import android.util.Log
import com.example.tsukeysmobile.Navigation.Screen
import com.example.tsukeysmobile.Requests.Interface.KeysInterface
import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import com.example.tsukeysmobile.Views.ChangeTransportedParams
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

const val AUTHORIZE_TOKEN = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImhlbmRvQGV4YW1wbGUuY29tIiwibmJmIjoxNzA4OTY0NjUzLCJleHAiOjE3MDg5NjU1NTMsImlhdCI6MTcwODk2NDY1MywiaXNzIjoiSldUVG9rZW4iLCJhdWQiOiJIdW1hbiJ9.6XY2kGdONukMyvpUJYpaDvCpGNPktCLQfDCG65ajy8c"
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
}