package com.myasser.wafrly.viewmodels

import android.content.Context
import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.myasser.wafrly.repository.AccountRepository


class LoginViewModel(context: Context):ViewModel() {
    private val loginRepo=AccountRepository(context)
    fun login(email: String, password: String): LiveData<Boolean> {
        return loginRepo.login(email, password)
    }
    fun register(email: String, password: String): LiveData<Boolean> {
        return loginRepo.register(email, password)
    }
    fun showGoogleLogin(): LiveData<Intent> {
        return loginRepo.showGoogleLogin()
    }
    fun handleGoogleLogin(task: Task<GoogleSignInAccount>):LiveData<Boolean> {
     return loginRepo.handleGoogleLogin(task)
    }
}