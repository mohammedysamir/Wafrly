package com.myasser.wafrly.viewmodels

import com.myasser.wafrly.repository.RetrofitProductRepository

class ProductsViewModel {
    private val productRepo = RetrofitProductRepository()

    fun getProducts() = productRepo.getAllProducts()
    fun getProductById(id:Int)= productRepo.getProductById(id)
    fun getProductsByCategory(category:String)= productRepo.getProductsByCategory(category)
}
//todo: add more filters by price (asc, desc) and by name (asc, desc)
// product vm must be passed the product Id, send it to ProductRepo and get product back by id to view, and then navigate with intent extras
//todo: here we must handle product item click -> navigate to ProductScreen and fetch data by id