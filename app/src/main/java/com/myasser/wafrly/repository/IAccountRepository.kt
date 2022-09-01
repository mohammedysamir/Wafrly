package com.myasser.wafrly.repository

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.myasser.wafrly.models.data.Product

interface IAccountRepository {
    fun showGoogleLogin(): MutableLiveData<Intent> //show login prompt
    fun handleGoogleLogin(task: Task<GoogleSignInAccount>)//validate info and login
    fun login(email: String, password: String)//validate info and login
    fun register(email: String, password: String)
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): MutableLiveData<List<Product>>
    fun clearCart()
    fun getFavorite(): MutableLiveData<List<Product>>
    fun notifyPurchase(bill: Double)
}