package com.myasser.wafrly.viewmodels

import com.myasser.wafrly.repository.RetrofitProductRepository

class ProductViewModel {
    private val productRepository = RetrofitProductRepository()
    fun getProductById(id: Int) = productRepository.getProductById(id)
}