package com.myasser.wafrly.repository

import com.myasser.wafrly.models.data.Product

interface IProductRepository {
    fun getAllProducts(): List<Product>
    fun getProductByName(name: String): List<Product> // may return list for same-name products
    fun getTopRatedProducts(): List<Product>
    fun getTopSalesProducts(): List<Product>
}