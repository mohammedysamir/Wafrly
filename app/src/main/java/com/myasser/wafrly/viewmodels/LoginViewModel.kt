package com.myasser.wafrly.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myasser.wafrly.repository.AccountRepository


class LoginViewModel(context: Context):ViewModel() {
    private val loginRepo=AccountRepository(context)
    fun login(email: String, password: String): LiveData<Boolean> {
        return loginRepo.login(email, password)
    }
    fun register(email: String, password: String): LiveData<Boolean> {
        return loginRepo.register(email, password)
    }
    fun googleLogin(): LiveData<Boolean> {
        return loginRepo.googleLogin()
    }
}