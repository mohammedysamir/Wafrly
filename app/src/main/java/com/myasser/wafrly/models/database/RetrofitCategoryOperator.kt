package com.myasser.wafrly.models.database

import com.myasser.wafrly.models.data.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

//todo: may need to change architecture and mark RetrofitCategoryOperator as interface instead of abstract class
abstract class RetrofitCategoryOperator : CategoryOperators {
    @GET("products/categories")
    abstract override fun getAllCategories(): Call<List<String>>
    @GET("products/categories/{category}")
    abstract override fun getCategoryByName(@Path("category") name:String): Call<List<Product>>
}
/*
* Define retrofit methods here, declare the builder then call functions from the Repo class.
* */