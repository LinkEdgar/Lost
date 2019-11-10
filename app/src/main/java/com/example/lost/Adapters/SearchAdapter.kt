package com.example.lost.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lost.R

class SearchAdapter(var context: Context, var data: ArrayList<String>? = ArrayList()): RecyclerView.Adapter<SearchAdapter.SearchViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.lost_card, parent, false)
        return SearchViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        //TODO bind and start any logic based operators 
    }


    class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
