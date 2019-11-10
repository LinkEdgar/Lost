package com.example.lost.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.lost.R
import kotlinx.android.synthetic.main.fragment_report_prompt.view.*

class ReportPromptFragment : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val rootView = inflater.inflate(R.layout.fragment_report_prompt, container, false)
        rootView.next_bt.setOnClickListener{
            view!!.findNavController().navigate(R.id.action_reportPromptFragment_to_petFormFragment)
        }
        return rootView
    }
}