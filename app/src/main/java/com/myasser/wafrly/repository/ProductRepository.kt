package com.myasser.wafrly.repository

import com.myasser.wafrly.models.data.Product

class ProductRepository:IProductRepository {
    override fun getAllProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getProductByName(name: String): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedProducts(): List<Product> {
        TODO("Not yet implemented")
    }

    override fun getTopSalesProducts(): List<Product> {
        TODO("Not yet implemented")
    }
}