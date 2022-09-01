package com.myasser.wafrly.models.database

import com.myasser.wafrly.models.data.Product
import retrofit2.Call

interface CategoryOperators {
    suspend fun getAllCategories(): Call<List<String>>
    suspend fun getCategoryByName(name: String): Call<List<Product>>
}