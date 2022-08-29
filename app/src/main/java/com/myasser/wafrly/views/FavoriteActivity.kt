package com.myasser.wafrly.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.ProductRecyclerViewAdapter
import com.myasser.wafrly.viewmodels.FavoriteViewModel

class FavoriteActivity : AppCompatActivity() {
    private val favoriteViewModel = FavoriteViewModel(this)
    private lateinit var favoriteRecyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        favoriteRecyclerView = findViewById(R.id.favoriteRecyclerView)
        favoriteViewModel.getFavorite().observe(this) {
            favoriteRecyclerView.adapter = ProductRecyclerViewAdapter(it, this)
        }
    }
}