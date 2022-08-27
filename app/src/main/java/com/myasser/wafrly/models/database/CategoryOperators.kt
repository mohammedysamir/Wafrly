package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Category

interface CategoryOperators {
    fun getAllDepartments(): LiveData<List<Category>>
}