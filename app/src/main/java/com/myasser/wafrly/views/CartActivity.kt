package com.myasser.wafrly.views

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.recyclerview.widget.RecyclerView
import com.myasser.wafrly.R
import com.myasser.wafrly.adapters.ProductRecyclerViewAdapter
import com.myasser.wafrly.viewmodels.CartViewModel

class CartActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var cartRecyclerView: RecyclerView
    private lateinit var cartViewModel: CartViewModel
    private var adapter: ProductRecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        cartRecyclerView = findViewById(R.id.cartRecyclerView)
        cartViewModel = CartViewModel(this)
        cartViewModel.getCartItems().observe(this) {
            adapter = ProductRecyclerViewAdapter(it, this)
            cartRecyclerView.adapter = adapter!!
        }
        //get bill
        cartViewModel.calculateBill(adapter?.products!!).observe(this) {
            //show bill
            findViewById<TextView>(R.id.cartBillTextView).text = "Bill: $ $it"
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.cartCheckoutButton -> {
                //show checkout dialog with parameterized bill amount.
                val dialog = Dialog(this@CartActivity)
                dialog.setContentView(R.layout.checkout_confirm)
                dialog.setCancelable(false)
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                val descriptionTextView = dialog.findViewById<TextView>(R.id.descriptionTextView)
                val confirmButton = dialog.findViewById<AppCompatButton>(R.id.confirmCheckoutButton)
                val cancelButton = dialog.findViewById<AppCompatButton>(R.id.cancelCheckoutButton)
                descriptionTextView.text =
                    v.context.resources.getString(R.string.do_you_confirm_this_checkout_with_bill_amount,
                        cartViewModel.calculateBill(adapter?.products!!).value.toString())
                //set listeners to button
                confirmButton.setOnClickListener {
                    //notify
                    Toast.makeText(this@CartActivity,
                        getString(R.string.checkout_confirmed_msg),
                        Toast.LENGTH_LONG).show()
                    //when confirm is clicked, delete all cart items and go to home screen, send notification to user.
                    //todo: send notifications to user after the checkout
                    //clear cart
                    cartViewModel.clearCart()

                    //dismiss dialog
                    dialog.dismiss()

                    //go to home screen
                    startActivity(Intent(this@CartActivity, CategoryActivity::class.java))
                    finish()
                }
                cancelButton.setOnClickListener {
                    dialog.dismiss()
                }
                dialog.show()

            }
        }
    }
}