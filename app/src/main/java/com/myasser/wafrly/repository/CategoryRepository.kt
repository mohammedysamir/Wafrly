package com.myasser.wafrly.repository

import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.models.database.RetrofitCategoryOperator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//category base url: https://fakestoreapi.com/products/categories
//specific category: baseurl+categoryName
class CategoryRepository : ICategoryRepository {
    private val baseUrl = "https://fakestoreapi.com/products/categories"

    //initiate the retrofit builder and return response in the form of a MutableLiveData <list of strings>
    var retroBuilder: Retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(
        GsonConverterFactory.create()).build()
    private var categoryService: RetrofitCategoryOperator =
        retroBuilder.create(RetrofitCategoryOperator::class.java)

    override fun getAllCategories(): MutableLiveData<List<String>> {
        val categories = MutableLiveData<List<String>>()
        categoryService.getAllCategories().enqueue(object : retrofit2.Callback<List<String>> {
            override fun onResponse(
                call: retrofit2.Call<List<String>>,
                response: retrofit2.Response<List<String>>,
            ) {
                categories.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<List<String>>, t: Throwable) {
                categories.value = null
            }
        })
        return categories
    }

    override fun getCategoryByName(name: String): MutableLiveData<List<Product>> {
        val products = MutableLiveData<List<Product>>()
        categoryService.getCategoryByName(name).enqueue(object : retrofit2.Callback<List<Product>> {
            override fun onResponse(
                call: retrofit2.Call<List<Product>>,
                response: retrofit2.Response<List<Product>>,
            ) {
                products.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<List<Product>>, t: Throwable) {
                products.value = null
            }
        })
        return products
    }

}
//todo: test Category repository