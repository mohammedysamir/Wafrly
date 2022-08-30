package com.myasser.wafrly.views

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.ProductRecyclerViewAdapter
import com.myasser.wafrly.viewmodels.CartViewModel

class CartActivity : AppCompatActivity() {
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartViewModel: CartViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartViewModel = CartViewModel(this)
        var adapter: ProductRecyclerViewAdapter?=null
        cartViewModel.getCartItems().observe(this) {
            adapter = ProductRecyclerViewAdapter(it, this)
            cartRecyclerView.adapter = adapter!!
        }
        //get bill
        cartViewModel.calculateBill(adapter?.products!!).observe(this) {
            //show bill
            findViewById<TextView>(R.id.cartBillTextView).text="Bill: $ $it"
        }
    }
    //implement checkout
}