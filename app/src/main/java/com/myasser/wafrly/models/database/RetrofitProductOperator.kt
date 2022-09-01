package com.myasser.wafrly.models.database

import com.myasser.wafrly.models.data.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

abstract class RetrofitProductOperator : ProductOperator {
    @GET("products")
    abstract override fun getAllProducts(): Call<List<Product>>

    @GET("products/category/{category}")
    abstract override fun getProductsByCategory(@Path("category") category: String): Call<List<Product>>

    @GET("products/{id}")
    abstract override fun getProductById(@Path("id") id: Int): Call<Product>
}
