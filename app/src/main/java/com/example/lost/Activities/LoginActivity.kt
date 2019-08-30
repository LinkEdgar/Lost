package com.example.lost.Activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.lost.R
import com.example.lost.ViewModels.LoginFragmentViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginFragmentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(LoginFragmentViewModel::class.java)
    }

    private fun setUi(){

    }
}
