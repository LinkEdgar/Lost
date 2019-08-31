package com.example.lost.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lost.Adapters.IntroAdapter
import com.example.lost.R
import kotlinx.android.synthetic.main.fragment_intro.view.*

class IntroFragment : Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: IntroAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_intro, container, false)

        recyclerView = rootView.recycler_view
        val list = ArrayList<String>()
        //TODO remove static string
        list.add("")
        adapter = IntroAdapter(context!!, list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context!!)
        return rootView
    }
}