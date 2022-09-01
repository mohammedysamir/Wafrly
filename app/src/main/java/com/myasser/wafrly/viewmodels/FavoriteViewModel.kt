package com.myasser.wafrly.viewmodels

import android.content.Context
import com.myasser.wafrly.repository.AccountRepository

class FavoriteViewModel(context: Context) {
    private val accountRepository = AccountRepository(context)
    fun getFavorite()= accountRepository.getFavorite()
}