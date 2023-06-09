package com.example.myapplication

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RecordsApi {
    @GET("/photos")
    suspend fun getRecords() : List<Records>
}