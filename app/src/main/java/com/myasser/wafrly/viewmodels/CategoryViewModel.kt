package com.myasser.wafrly.viewmodels

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.repository.RetrofitCategoryRepository

class CategoryViewModel {
    val categoryRepo = RetrofitCategoryRepository()
    fun getAllCategories(): LiveData<List<String>> = categoryRepo.getAllCategories()
    fun getCategoryByName(name: String): LiveData<List<Product>> = categoryRepo.getCategoryByName(name)
}