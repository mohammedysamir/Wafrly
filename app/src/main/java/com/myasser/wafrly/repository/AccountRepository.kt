package com.myasser.wafrly.repository

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.models.database.AccountOperators
import com.myasser.wafrly.models.database.FireAccountOperator

class AccountRepository(val context: Context) : IAccountRepository {
    private val accountOperators: AccountOperators = FireAccountOperator(context)

    override fun googleLogin(): MutableLiveData<Boolean> {
        return MutableLiveData<Boolean>(accountOperators.googleLogin())
    }

    override fun login(email: String, password: String): MutableLiveData<Boolean> {
        return MutableLiveData<Boolean>(accountOperators.login(email, password))
    }

    override fun register(email: String, password: String): MutableLiveData<Boolean> {
        return MutableLiveData<Boolean>(accountOperators.register(email, password))
    }

    override fun addToCart(product: Product) {
        accountOperators.addToCart(product)
    }

    override fun addToFavorite(product: Product) {
        accountOperators.addToFavorite(product)
    }

    override fun removeFromCart(product: Product) {
        accountOperators.removeFromCart(product)

    }

    override fun removeFromFavorite(product: Product) {
        accountOperators.removeFromFavorite(product)
    }

    override fun getCart(): MutableLiveData<List<Product>> {
        return MutableLiveData(accountOperators.getCart())
    }

    override fun getFavorite(): MutableLiveData<List<Product>> {
        return MutableLiveData(accountOperators.getFavorite())
    }
}