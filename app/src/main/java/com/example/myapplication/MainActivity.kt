package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private var mainActivityMainBinding:ActivityMainBinding?=null
    private var viewModel: MainViewModel?=null
    private lateinit var movieAdapter : RecordsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding= DataBindingUtil.setContentView(this,R.layout.activity_main)
        val recordsApi = RetrofitHelper.getInstance().create(RecordsApi::class.java)
        val repository = MainRepository(recordsApi)
        viewModel=ViewModelProviders.of(this, RecordsModelFactory(repository,this)).get(MainViewModel::class.java)
        prepareRecyclerView()
        GlobalScope.launch {
            viewModel!!.getAllRecords()
        }
        viewModel!!.observeLiveData().observe(this, Observer { recordsList ->
            movieAdapter.setRecordsList(recordsList)
        })
    }
    private fun prepareRecyclerView() {
        movieAdapter = RecordsAdapter(viewModel!!)
        mainActivityMainBinding!!.rvMovies.apply {
            layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.VERTICAL,false)
            adapter = movieAdapter
        }
    }
}