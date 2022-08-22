package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Department

class FireDepartmentOperator : DepartmentOperators {
    override fun getAllDepartments(): LiveData<List<Department>> {
        TODO("Not yet implemented")
    }
}