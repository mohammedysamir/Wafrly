package com.myasser.wafrly.repository

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.myasser.wafrly.models.data.Product

interface IAccountRepository {
    fun googleLogin(): MutableLiveData<Intent> //validate info and login
    fun login(email: String, password: String):MutableLiveData<Boolean> //validate info and login
    fun register(email: String, password: String):MutableLiveData<Boolean>
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): MutableLiveData<List<Product>>
    fun clearCart()
    fun getFavorite(): MutableLiveData<List<Product>>
}