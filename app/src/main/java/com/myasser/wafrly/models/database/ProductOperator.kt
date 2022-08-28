package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product
import retrofit2.Call

interface ProductOperator {
    fun getAllProducts(): Call<List<Product>>
    fun getProductById(id: Int): Call<Product>
}