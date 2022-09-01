package com.myasser.wafrly.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.repository.AccountRepository
import com.myasser.wafrly.repository.RetrofitCategoryRepository

class CategoryViewModel(context: Context) : ViewModel() {
    private val categoryRepo = RetrofitCategoryRepository()
    private val accountRepository = AccountRepository(context = context)
    fun getAllCategories(): LiveData<List<String>> = categoryRepo.getAllCategories()
    fun getCategoryByName(name: String): LiveData<List<Product>> =
        categoryRepo.getCategoryByName(name)

    fun logout() = accountRepository.logout()
}