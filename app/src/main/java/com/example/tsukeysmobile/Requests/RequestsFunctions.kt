package com.example.tsukeysmobile.Requests

import com.example.tsukeysmobile.Requests.Interface.KeysInterface
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val AUTHORIZE_TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImhhbmRpZUBleGFtcGxlLmNvbSIsIm5iZiI6MTcwODg1MTExNywiZXhwIjoxNzA4ODUyMDE3LCJpYXQiOjE3MDg4NTExMTcsImlzcyI6IkpXVFRva2VuIiwiYXVkIjoiSHVtYW4ifQ.2NzEve5kMhFI-fyHPFteCpddb_UEBbCk9fnkI29j7Y0"
const val BASE_URL = "http://89.111.174.112:8181/swagger/index.html/"

class RequestsFunctions {

    private fun GetKeys(year: Int, month: Int, day: Int, timeId: Int){
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(KeysInterface::class.java)
        val retrofitData = retrofitBuilder.getKeys(AUTHORIZE_TOKEN,year, month, day, timeId, "AvaliableKeys")
    }
}