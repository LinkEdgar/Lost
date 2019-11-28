package com.example.lost.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.lost.R
import com.example.lost.Registration
import com.example.lost.Resource
import com.example.lost.viewModels.CreateAccountViewModel
import com.example.lost.databinding.FragmentCreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class CreateAccountFragment : Fragment(){

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: CreateAccountViewModel
    private lateinit var binding: FragmentCreateAccountBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_account, container, false)
        auth = FirebaseAuth.getInstance()
        viewModel = ViewModelProviders.of(this).get(CreateAccountViewModel::class.java)
        binding = FragmentCreateAccountBinding.bind(rootView)
        binding.viewModel = viewModel

        binding.registerBt.setOnClickListener{ onRegisterClicked() }
        return rootView
    }

    private fun onRegisterClicked(){
        val registration = Registration(binding.nameEt.text.toString(),
            binding.emailEt.text.toString(),
            binding.passwordEt.text.toString(),
            binding.confirmPassEt.text.toString())

        viewModel.onRegisterClicked(registration).observe(viewLifecycleOwner, Observer { isValid ->
            if(isValid){
                registerUser(registration)
            }else{
                Toast.makeText(context, "Please enter valid credentials", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun registerUser(registration: Registration){
        viewModel.registerUser(auth, registration, activity!! ).observe(viewLifecycleOwner, Observer { response ->

            when(response.status){
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context!!, "Failed to create account. Please try again", Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.switchToLogin(view!!)
                }
            }
        })
    }
}