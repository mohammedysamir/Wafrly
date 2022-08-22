package com.myasser.wafrly.models.database

import androidx.lifecycle.LiveData
import com.myasser.wafrly.models.data.Product

class FireProductOperator:ProductOperator {
    override fun getAllProducts(): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getProductByName(name: String): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getTopRatedProducts(): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getTopSalesProducts(): LiveData<List<Product>> {
        TODO("Not yet implemented")
    }
}
