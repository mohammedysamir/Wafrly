package com.myasser.wafrly.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.repository.AccountRepository

class CartViewModel(context: Context): ViewModel() {
    private val accountRepo = AccountRepository(context = context)
    fun getCartItems() = accountRepo.getCart()
    fun calculateBill(products: List<Product>): MutableLiveData<Double> {
        var bill = 0.0
        for (product in products) {
            bill += product.price
        }
        return MutableLiveData(bill)
    }
    fun clearCart() = accountRepo.clearCart()
    fun notifyPurchase(bill: Double) = accountRepo.notifyPurchase(bill)
}