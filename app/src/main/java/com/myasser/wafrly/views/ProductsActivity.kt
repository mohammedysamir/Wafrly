package com.myasser.wafrly.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.ProductRecyclerViewAdapter
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.viewmodels.ProductsViewModel

class ProductsActivity : AppCompatActivity() {
    private lateinit var productsRecyclerView: RecyclerView
    private val productsViewModel = ProductsViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        val categoryName = intent.getStringExtra("category name")!!
        //set category name
        findViewById<TextView>(R.id.categoryTextView).text = categoryName
        //fetch products list using category name from the vm
        productsRecyclerView = findViewById(R.id.productsRecyclerView)
        //observe vm
        productsViewModel.getProductsByCategory(categoryName).observe(this) {
            productsRecyclerView.adapter = ProductRecyclerViewAdapter(it, this)
        }
    }
}