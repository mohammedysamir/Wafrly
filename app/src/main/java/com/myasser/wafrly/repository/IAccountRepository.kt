package com.myasser.wafrly.repository

import com.myasser.wafrly.models.data.Product

interface IAccountRepository {
    fun loginValidation(email: String, password: String):Boolean //validate info and login
    fun register(email: String, password: String)
    fun addToCart(product: Product)
    fun addToFavorite(product: Product)
    fun removeFromCart(product: Product)
    fun removeFromFavorite(product: Product)
    fun getCart(): List<Product>
    fun getFavorite(): List<Product>
}