package com.myasser.wafrly.models.database

import com.myasser.wafrly.models.data.Product
import retrofit2.Call

interface CategoryOperators {
    fun getAllCategories(): Call<List<String>>
    fun getCategoryByName(name: String): Call<List<Product>>
}