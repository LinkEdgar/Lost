package com.example.lost.ViewModels

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.lost.Activities.LoginActivity
import com.example.lost.R

class LoginFragmentViewModel : ViewModel(){

    private var isValidCredentials: MutableLiveData<Boolean> = MutableLiveData()

    fun validateCredentials(username: String, password: String):Boolean{
        return true
    }

    fun onLoginClicked(username: String, password: String): MutableLiveData<Boolean>{
        isValidCredentials.value = validateCredentials(username, password)
        return isValidCredentials
    }

    fun switchActivities(context: Context){

        //todo switch the destination
        val intent = Intent(context, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun switchToCreateAccount(view: View){
        view.findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
    }
}