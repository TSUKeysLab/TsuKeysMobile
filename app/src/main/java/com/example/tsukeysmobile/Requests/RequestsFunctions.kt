package com.example.tsukeysmobile.Requests

import com.example.tsukeysmobile.Requests.Interface.KeysInterface
import com.example.tsukeysmobile.Requests.Keys.KeysDataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val AUTHORIZE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImhhbmRpZUBleGFtcGxlLmNvbSIsIm5iZiI6MTcwODg1MTExNywiZXhwIjoxNzA4ODUyMDE3LCJpYXQiOjE3MDg4NTExMTcsImlzcyI6IkpXVFRva2VuIiwiYXVkIjoiSHVtYW4ifQ.2NzEve5kMhFI-fyHPFteCpddb_UEBbCk9fnkI29j7Y0"
const val BASE_URL = "http://89.111.174.112:8181/swagger/index.html/"

class RequestsFunctions {

    fun GetKeys(year: Int, month: Int, day: Int, timeId: Int): List<KeysDataItem> {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(KeysInterface::class.java)
        val retrofitData = retrofitBuilder.getKeys(AUTHORIZE_TOKEN,year, month, day, timeId, "AvaliableKeys")
        var keys: List<KeysDataItem> = mutableListOf()


        retrofitData.enqueue(object : Callback<List<KeysDataItem>?> {
            override fun onResponse(
                call: Call<List<KeysDataItem>?>,
                response: Response<List<KeysDataItem>?>
            ) {
                var responseBody = response.body()!!
                keys = responseBody
            }

            override fun onFailure(call: Call<List<KeysDataItem>?>, t: Throwable) {
                throw Error("")
            }
        })
        return keys
    }
}