package com.example.lost.viewModels

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.lost.Activities.MainActivity
import com.example.lost.R
import com.example.lost.Registration
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class CreateAccountViewModel : ViewModel(){

    private lateinit var areFieldsValid: MutableLiveData<Boolean>

    private lateinit var isRegistrationSuccessful: MutableLiveData<Boolean>

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
        areFieldsValid = MutableLiveData()
        areFieldsValid.value = validateFields(registration)
        return areFieldsValid
    }

    fun registerUser(auth: FirebaseAuth, registration: Registration, activity: Activity) :MutableLiveData<Boolean>{

        isRegistrationSuccessful = MutableLiveData()
        auth.createUserWithEmailAndPassword(registration.email, registration.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.e(TAG, "createUserWithEmail:success")
                    isRegistrationSuccessful.postValue(true)
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e(TAG, "createUserWithEmail:failure", task.exception)
                    isRegistrationSuccessful.postValue(false)
                }
            }
        return isRegistrationSuccessful
    }

    fun switchToLogin(view: View){
        view.findNavController().navigate(R.id.action_createAccountFragment_to_loginFragment)
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