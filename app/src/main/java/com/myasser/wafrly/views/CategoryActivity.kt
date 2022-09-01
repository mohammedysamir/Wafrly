package com.myasser.wafrly.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.CategoryGridViewAdapter
import com.myasser.wafrly.viewmodels.CategoryViewModel

class CategoryActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var categoryGridView: GridView
    private var categoryVM: CategoryViewModel = CategoryViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        //grid view
        categoryGridView = findViewById(R.id.categoryGridView)
        categoryGridView.adapter = CategoryGridViewAdapter(categoryVM.getAllCategories().value!!)
        //icons listeners
        findViewById<ImageView>(R.id.goToFavoritesIcon).setOnClickListener(this)
        findViewById<ImageView>(R.id.goToCartIcon).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.goToFavoritesIcon -> {
                startActivity(Intent(this, FavoriteActivity::class.java))
            }
            R.id.goToCartIcon -> {
                startActivity(Intent(this, CartActivity::class.java))
            }
        }
    }
}
/*
* Today's task: 28/8/2022
*   1. complete view for category, product and products views
*   2. complete viewModels for them.
*   3. design the views in XML.
* */