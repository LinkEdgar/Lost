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
import com.example.lost.Resource
import com.example.lost.viewModels.LoginFragmentViewModel
import com.example.lost.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(){

    private lateinit var auth: FirebaseAuth
    private lateinit var viewModel: LoginFragmentViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_login, container, false)

        binding = FragmentLoginBinding.bind(rootView)
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
        auth = FirebaseAuth.getInstance()

        setUi()
        return rootView
    }

    private fun setUi(){
        binding.createAccountBt.setOnClickListener { viewModel.switchToCreateAccount(view!!)}
        binding.signInBt.setOnClickListener{ onLoginClicked() }
        //TODO add forgot password logic
    }

    private fun onLoginClicked(){
        viewModel.onLoginClicked( binding.usernameEt.text.toString().trim(), binding.passwordEt.text.toString().trim())
            .observe(viewLifecycleOwner, Observer {isValid ->
                if(isValid) {
                    signIn()
                } else {
                    Toast.makeText(context!!, "Invalid credentials", Toast.LENGTH_LONG).show()
                }
            })
    }

    private fun signIn(){
        viewModel.signIn( auth, activity!!).observe(viewLifecycleOwner, Observer { response ->

            when(response.status){
                Resource.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context!!, "Error", Toast.LENGTH_LONG).show()
                }
                Resource.Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    viewModel.switchToHome(context!!)
                }
            }
        })
    }

    override fun onStart() {
        super.onStart()
        if(auth.currentUser != null){
            viewModel.switchToHome(context!!)
        }
    }
}