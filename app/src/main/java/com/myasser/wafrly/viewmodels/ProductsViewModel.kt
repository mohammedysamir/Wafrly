package com.myasser.wafrly.viewmodels

import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.repository.RetrofitProductRepository

class ProductsViewModel {
    private val productRepo = RetrofitProductRepository()

    fun getProducts() = productRepo.getAllProducts()
    fun getProductById(id: Int) = productRepo.getProductById(id)
    fun getProductsByCategory(category: String) = productRepo.getProductsByCategory(category)

    //todo: test sorting
    fun getProductsSortedByPrice(category: String, isDescending: Boolean): List<Product> {
        return if (!isDescending)
            productRepo.getProductsByCategory(category).value?.sortedBy { it.price }?.reversed()!!
        else
            productRepo.getProductsByCategory(category).value?.sortedByDescending { it.price }!!
    }

    fun getProductsSortedByName(category: String, isDescending: Boolean): List<Product> {
        return if (!isDescending)
            productRepo.getProductsByCategory(category).value?.sortedBy { it.title }?.reversed()!!
        else
            productRepo.getProductsByCategory(category).value?.sortedByDescending { it.title }!!
    }
}
// product vm must be passed the product Id, send it to ProductRepo and get product back by id to view, and then navigate with intent extras