package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.databinding.ActivityDetailBinding
import com.example.myapplication.databinding.ActivityMainBinding
import com.squareup.picasso.Picasso

class DetailActivity : AppCompatActivity() {
    private var activityDetailBinding: ActivityDetailBinding?=null
    private var url:String?=null
    private var description: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        if (intent!=null&&intent.hasExtra("title"))
        {
            description=  intent.getStringExtra("title")!!
        }
        if (intent!=null&&intent.hasExtra("url"))
        {
            url=  intent.getStringExtra("url")!!
        }
        Picasso.get().load(url).into(activityDetailBinding!!.movieImage)
        activityDetailBinding!!.movieName.text=description
    }

}