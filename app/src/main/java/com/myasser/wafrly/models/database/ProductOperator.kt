package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product

interface ProductOperator {
    fun getAllProducts(): LiveData<List<Product>>
    fun getProductByName(name: String): LiveData<List<Product>>
    fun getTopRatedProducts(): LiveData<List<Product>>
    fun getTopSalesProducts(): LiveData<List<Product>>
}