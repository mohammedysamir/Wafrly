package com.myasser.wafrly.models.database

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.myasser.wafrly.models.data.Product

interface AccountOperators {
    fun login(email: String, password: String): Boolean
    fun showGoogleLogin(): GoogleSignInClient
    fun handleGoogleLogin(task:Task<GoogleSignInAccount>): Boolean
    fun register(email: String, password: String): Boolean
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): List<Product>
    fun clearCart()
    fun getFavorite():List<Product>
    fun getCurrentUser():FirebaseUser?
}