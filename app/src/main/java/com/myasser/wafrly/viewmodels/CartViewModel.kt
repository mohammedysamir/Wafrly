package com.myasser.wafrly.viewmodels

import android.content.Context
import com.myasser.wafrly.repository.AccountRepository

class CartViewModel(private val context: Context) {
    private val accountRepo=AccountRepository(context = context)
    fun getCartItems()=accountRepo.getCart()
}