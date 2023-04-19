package com.example.myapplication;

import javax.inject.Inject

class MainRepository constructor(private val retrofitApi: RecordsApi){
    suspend fun getAllRecords()=retrofitApi.getRecords()
}
