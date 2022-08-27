package com.myasser.wafrly.repository

import androidx.lifecycle.MutableLiveData
import com.myasser.wafrly.models.data.Product

interface ICategoryRepository {
    fun getAllCategories():MutableLiveData<List<String>>
    fun getCategoryByName(name:String):MutableLiveData<List<Product>>
}