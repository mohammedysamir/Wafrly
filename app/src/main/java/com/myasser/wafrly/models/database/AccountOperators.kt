package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product

interface AccountOperators {
    fun loginValidation(email: String, password: String): Boolean
    fun register(email: String, password: String)
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): LiveData<List<Product>>
    fun getFavorite(): LiveData<List<Product>>
}