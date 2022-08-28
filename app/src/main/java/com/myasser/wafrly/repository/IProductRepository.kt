package com.myasser.wafrly.repository

import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product

interface IProductRepository {
    fun getAllProducts(): MutableLiveData<List<Product>>
    fun getProductById(id: Int): MutableLiveData<Product> // may return list for same-name products
    fun getProductsByCategory(category: String): MutableLiveData<List<Product>>
}