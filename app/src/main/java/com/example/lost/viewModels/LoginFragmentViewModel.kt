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
import com.example.lost.Resource
import com.example.lost.Resource.Status
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginFragmentViewModel : ViewModel(){

    private var isValidCredentials: MutableLiveData<Boolean> = MutableLiveData()

    private var signIn: MutableLiveData<Resource<FirebaseUser>> = MutableLiveData()

    private var username: String = ""
    private var password: String = ""

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
        isValidCredentials = MutableLiveData()
        this.username = username
        this.password = password
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

    fun checkIfUserIsLoggedIn(currentUser: FirebaseUser?, context: Context){
        if(currentUser != null){
            switchToHome(context)
        }
    }

    fun signIn(auth : FirebaseAuth, activity: Activity): MutableLiveData<Resource<FirebaseUser>>{
        signIn = MutableLiveData()
        signIn.value = Resource(Status.LOADING)
        auth.signInWithEmailAndPassword(username, password)
            .addOnCompleteListener(activity){ task ->
                if(task.isSuccessful){
                    signIn.postValue(Resource(Status.SUCCESS, auth.currentUser))
                    Log.e("SignIn", "Success")
                } else {
                    Log.e("SignIn", "Failure")
                    signIn.postValue(Resource(Status.ERROR))
                }

            }
        return signIn
    }
}