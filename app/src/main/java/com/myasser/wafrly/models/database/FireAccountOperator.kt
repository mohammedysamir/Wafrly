package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product

class FireAccountOperator:AccountOperators {
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

    override fun getCart(): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }
}