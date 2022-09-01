package com.myasser.wafrly.repository

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.models.database.AccountOperators
import com.myasser.wafrly.models.database.FireAccountOperator

class AccountRepository(val context: Context) : IAccountRepository {
    private val accountOperators: AccountOperators = FireAccountOperator(context)

    override fun showGoogleLogin(): MutableLiveData<Intent> {
        return MutableLiveData<Intent>(accountOperators.showGoogleLogin().signInIntent)
    }

    override fun handleGoogleLogin(task: Task<GoogleSignInAccount>) {
        accountOperators.handleGoogleLogin(task)
    }

    override fun login(email: String, password: String) {
        accountOperators.login(email, password)
    }

    override fun register(email: String, password: String) {
        accountOperators.register(email, password)
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

    override fun clearCart() {
        accountOperators.clearCart()
    }

    override fun getFavorite(): MutableLiveData<List<Product>> {
        return MutableLiveData(accountOperators.getFavorite())
    }

    override fun notifyPurchase(bill: Double) {
        accountOperators.notifyPurchase(bill)
    }

    override fun logout() {
        accountOperators.logout()
    }
}