package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.icu.text.AlphabeticIndex.Record
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject
import javax.inject.Singleton


class MainViewModel  constructor(private val repository: MainRepository, val context: Context) : ViewModel() {
    val recordsListLiveData = MutableLiveData<List<Records>>()
    var intent: Intent? = null
    suspend fun getAllRecords(){
        val recordsList = repository.getAllRecords()
        recordsListLiveData.postValue(recordsList)
    }
    fun observeLiveData() : LiveData<List<Records>> {
        return recordsListLiveData
    }
    fun upcommingActions(url: String, description: String) {
        intent = Intent(context, DetailActivity::class.java)
        intent!!.putExtra(
            "title",
            description
        )
        intent!!.putExtra(
            "url",
            url
        )
        context.startActivity(intent)
    }
}
