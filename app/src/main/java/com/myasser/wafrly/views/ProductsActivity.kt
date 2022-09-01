package com.myasser.wafrly.views

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.color.MaterialColors
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.ProductRecyclerViewAdapter
import com.myasser.wafrly.viewmodels.ProductsViewModel

class ProductsActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var productsRecyclerView: RecyclerView
    private val productsViewModel = ProductsViewModel()
    private lateinit var adapter: ProductRecyclerViewAdapter
    private val categoryName = intent.getStringExtra("category name")!!

    companion object {
        var sortedByPrice: Boolean = false
        var sortedByName: Boolean = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
        //set category name
        findViewById<TextView>(R.id.categoryTextView).text = categoryName
        //fetch products list using category name from the vm
        productsRecyclerView = findViewById(R.id.productsRecyclerView)
        //observe vm
        productsViewModel.getProductsByCategory(categoryName).observe(this) {
            adapter = ProductRecyclerViewAdapter(it, this)
            productsRecyclerView.adapter = adapter
        }
    }

    override fun onClick(v: View?) {
        val onPrimaryColor =
            MaterialColors.getColor(v?.context!!,
                com.google.android.material.R.attr.colorOnPrimary,
                Color.BLACK)
        val whiteColor = Color.WHITE
        when (v?.id) {
            R.id.sortAlphabeticalImageView -> {
                //re-fill recycler view with products sorted alphabetically
                sortedByName = !sortedByName
                adapter = ProductRecyclerViewAdapter(productsViewModel.getProductsSortedByName(
                    categoryName,
                    sortedByName), v.context)
                productsRecyclerView.adapter = adapter
                //change tint and background color
                //todo: test color change of icon
                val alphaIcon: ImageView = v as ImageView
                if (sortedByName) {
                    alphaIcon.setBackgroundColor(onPrimaryColor)
                    alphaIcon.setColorFilter(whiteColor)
                } else {
                    alphaIcon.setBackgroundColor(Color.TRANSPARENT)
                    alphaIcon.setColorFilter(onPrimaryColor)
                }
            }
            R.id.sortPriceImageView -> {
                //re-fill recycler view with products sorted by price
                sortedByPrice = !sortedByPrice
                adapter = ProductRecyclerViewAdapter(productsViewModel.getProductsSortedByPrice(
                    categoryName,
                    sortedByPrice), v.context)
                productsRecyclerView.adapter = adapter

                //todo: test color change of icon
                val priceIcon: ImageView = v as ImageView
                if (sortedByPrice) {
                    priceIcon.setBackgroundColor(onPrimaryColor)
                    priceIcon.setColorFilter(whiteColor)
                } else {
                    priceIcon.setBackgroundColor(Color.TRANSPARENT)
                    priceIcon.setColorFilter(onPrimaryColor)
                }
            }
        }
    }
}
//when sorting is clicked .. change background color and tint color of the icon