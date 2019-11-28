package com.example.lost.viewModels

import android.app.Activity
import android.content.ContentValues.TAG
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.findNavController
import com.example.lost.R
import com.example.lost.Registration
import com.example.lost.Resource
import com.google.firebase.auth.FirebaseAuth

class CreateAccountViewModel : ViewModel(){

    private lateinit var areFieldsValid: MutableLiveData<Boolean>

    private lateinit var isRegistrationSuccessful: MutableLiveData<Resource<Boolean>>

    fun onRegisterClicked(registration: Registration): MutableLiveData<Boolean>{
        areFieldsValid = MutableLiveData()
        areFieldsValid.value = validateFields(registration)
        return areFieldsValid
    }

    fun registerUser(auth: FirebaseAuth, registration: Registration, activity: Activity) :MutableLiveData<Resource<Boolean>>{

        isRegistrationSuccessful = MutableLiveData()
        isRegistrationSuccessful.value = Resource(Resource.Status.LOADING)

        auth.createUserWithEmailAndPassword(registration.email, registration.password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.e(TAG, "createUserWithEmail:success")
                    isRegistrationSuccessful.postValue(Resource(Resource.Status.SUCCESS,true))
                    val user = auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Log.e(TAG, "createUserWithEmail:failure", task.exception)
                    isRegistrationSuccessful.postValue(Resource(Resource.Status.ERROR,true))
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