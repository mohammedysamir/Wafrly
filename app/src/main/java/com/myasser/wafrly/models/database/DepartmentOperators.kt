package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Department

interface DepartmentOperators {
    fun getAllDepartments(): LiveData<List<Department>>
}