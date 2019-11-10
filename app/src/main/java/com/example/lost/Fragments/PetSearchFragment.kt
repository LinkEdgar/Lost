package com.example.lost.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lost.Adapters.SearchAdapter
import com.example.lost.R
import kotlinx.android.synthetic.main.fragment_pet_search.view.*


class PetSearchFragment: Fragment(){

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter:SearchAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_pet_search,container,false)

        recyclerView = rootView.recycler_view

        val list = ArrayList<String>()
        list.add("1")
        list.add("2")
        list.add("3")

        adapter = SearchAdapter(context!!, list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)


        return rootView
    }
}