package com.example.lost.viewModels

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.lost.Activities.MainActivity
import com.example.lost.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragmentViewModel : ViewModel(){

    private var isValidCredentials: MutableLiveData<Boolean> = MutableLiveData()

    private var signIn: MutableLiveData<FirebaseUser> = MutableLiveData()

    fun validateCredentials(username: String, password: String):Boolean{
        if(username.isEmpty()){
            return false
        }
        if(password.isEmpty()){
            return false
        }
        return true
    }

    fun onLoginClicked(username: String, password: String): MutableLiveData<Boolean>{
        isValidCredentials.value = validateCredentials(username, password)
        return isValidCredentials
    }

    fun switchToHome(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        context.startActivity(intent)
    }

    fun switchToCreateAccount(view: View){
        view.findNavController().navigate(R.id.action_loginFragment_to_createAccountFragment)
    }

    fun signIn(auth : FirebaseAuth, activity: Activity): MutableLiveData<FirebaseUser>{
        //TODO add pass and email
        auth.signInWithEmailAndPassword("edgar.r.link@gmail.com", "12345678")
            .addOnCompleteListener(activity){ task ->
                if(task.isSuccessful){
                    signIn.postValue(auth.currentUser)
                    Log.e("Yeet", "Success")
                } else {
                    Log.e("Yeet", "Failure")
                    signIn.postValue(null)
                }

            }
        return signIn
    }
}