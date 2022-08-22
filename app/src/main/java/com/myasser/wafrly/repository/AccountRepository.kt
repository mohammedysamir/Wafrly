package com.myasser.wafrly.repository

import com.myasser.wafrly.models.data.Product

class AccountRepository:IAccountRepository {
    override fun loginValidation(email: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun register(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override fun addToCart(product: Product) {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeFromCart(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override fun getCart(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): List<Product> {
        TODO("Not yet implemented")
    }
}