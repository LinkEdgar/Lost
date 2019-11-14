package com.example.lost.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.lost.R
import com.example.lost.viewModels.LoginFragmentViewModel
import com.example.lost.databinding.FragmentLoginBinding

class LoginFragment : Fragment(){

    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        binding = FragmentLoginBinding.bind(rootView)
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)

        setUi()
        return rootView
    }

    private fun setUi(){
        binding.createAccountBt.setOnClickListener { viewModel.switchToCreateAccount(view!!)}
        binding.signInBt.setOnClickListener{ viewModel.switchActivities(context!!)}
    }

}