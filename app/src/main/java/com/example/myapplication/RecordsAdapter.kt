package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RecordLayoutBinding
import com.squareup.picasso.Picasso

class RecordsAdapter(var viewModel: MainViewModel): RecyclerView.Adapter<RecordsAdapter.ViewHolder>() {
    private var recordsList = ArrayList<Records>()
    fun setRecordsList(movieList : List<Records>){
        this.recordsList = movieList as ArrayList<Records>
        notifyDataSetChanged()
    }
    class ViewHolder(val binding : RecordLayoutBinding) : RecyclerView.ViewHolder(binding.root)  {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecordLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load(recordsList[position].thumbnailUrl).into(holder.binding.movieImage);
        holder.binding.movieName.text = recordsList[position].title
        holder.binding.parentLayout.setOnClickListener {
            viewModel.upcommingActions(recordsList!!.get(position).thumbnailUrl,recordsList!!.get(position).title)

        }
    }
    override fun getItemCount(): Int {
        return recordsList.size
    }
}