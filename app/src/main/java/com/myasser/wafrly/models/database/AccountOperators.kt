package com.myasser.wafrly.models.database

import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product

interface AccountOperators {
    fun login(email: String, password: String): Boolean
    fun googleLogin(): Boolean
    fun register(email: String, password: String): Boolean
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): List<Product>
    fun clearCart()
    fun getFavorite():List<Product>
}