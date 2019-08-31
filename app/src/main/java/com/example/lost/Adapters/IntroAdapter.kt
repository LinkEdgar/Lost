package com.example.lost.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lost.R

class IntroAdapter(val context: Context,var  data: ArrayList<String>) : RecyclerView.Adapter<IntroAdapter.IntroViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {
        val rootView = LayoutInflater.from(context).inflate(R.layout.adopt_card, parent, false)

        return IntroViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        //TODO remove hardcoded value
        return 2
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {

    }

    class IntroViewHolder(view: View): RecyclerView.ViewHolder(view)
}