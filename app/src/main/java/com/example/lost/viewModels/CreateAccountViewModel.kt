package com.example.lost.viewModels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lost.Activities.MainActivity
import com.example.lost.Registration
import com.google.firebase.auth.FirebaseUser

class CreateAccountViewModel : ViewModel(){

    private lateinit var areFieldValid: MutableLiveData<Boolean>

    fun checkIfUserIsLoggedIn(currentUser: FirebaseUser?, context: Context){
        if(currentUser != null){
            switchToHome(context)
        }
    }

    fun switchToHome(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun onRegisterClicked(registration: Registration): MutableLiveData<Boolean>{
        areFieldValid = MutableLiveData()
        areFieldValid.value = validateFields(registration)
        return areFieldValid
    }

    fun registerUser(){

    }

    fun validateFields(registration: Registration): Boolean{
        if(registration.name.isEmpty()){
            return false
        }
        if(registration.email.isEmpty()){
            return false
        }
        if(registration.password.isEmpty() || registration.password.length < 6){
            return false
        }
        if(registration.confirmPassword.isEmpty() || registration.confirmPassword.length < 6){
            return false
        }
        if(registration.confirmPassword != registration.password){
            return false
        }
        return true
    }
}