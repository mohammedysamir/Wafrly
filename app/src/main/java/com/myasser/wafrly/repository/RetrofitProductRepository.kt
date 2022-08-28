package com.myasser.wafrly.repository

import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.models.database.RetrofitProductOperator
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// base url: https://fakestoreapi.com/products
// product by id: baseurl/id
// query: baseurl?sort=[desc | asc]& limit=[number]

class RetrofitProductRepository : IProductRepository {
    private val baseUrl = "https://fakestoreapi.com/"
    private val retrofitBuilder: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
        .build()
    private val productService: RetrofitProductOperator =
        retrofitBuilder.create(RetrofitProductOperator::class.java)

    override fun getAllProducts(): MutableLiveData<List<Product>> {
        val products = MutableLiveData<List<Product>>()
        productService.getAllProducts().enqueue(object : retrofit2.Callback<List<Product>> {
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

    override fun getProductById(id: Int): MutableLiveData<Product> {
        val product = MutableLiveData<Product>()
        productService.getProductById(id).enqueue(object : retrofit2.Callback<Product> {
            override fun onResponse(
                call: retrofit2.Call<Product>,
                response: retrofit2.Response<Product>,
            ) {
                product.value = response.body()
            }

            override fun onFailure(call: retrofit2.Call<Product>, t: Throwable) {
                product.value = null
            }
        })
        return product
    }

    override fun getProductsByCategory(category: String): MutableLiveData<List<Product>> {
        val products = MutableLiveData<List<Product>>()
        productService.getProductsByCategory(category)
            .enqueue(object : retrofit2.Callback<List<Product>> {
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