package com.myasser.wafrly.repository

import com.myasser.wafrly.models.data.Department

interface IDepartmentRepository {
    fun getAllDepartments():List<Department>
}