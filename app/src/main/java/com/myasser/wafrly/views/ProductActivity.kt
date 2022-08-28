package com.myasser.wafrly.views

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.repository.AccountRepository
import com.myasser.wafrly.viewmodels.ProductViewModel

class ProductActivity : AppCompatActivity(), View.OnClickListener {
    private val productViewModel = ProductViewModel()
    private val accountRepo = AccountRepository(this)
    private var isInCart = false
    private var isInFavorite = false
    private lateinit var currentProduct: Product

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)
        findViewById<AppCompatButton>(R.id.addToCartButton).setOnClickListener(this)
        findViewById<ImageView>(R.id.productFavoriteIcon).setOnClickListener(this)

        productViewModel.getProductById(intent.getIntExtra("product id", 0)).observe(this) {
            findViewById<TextView>(R.id.productCategoryTextView).text = it?.Category //category name
            Glide.with(this).load(it?.image).into(findViewById(R.id.productImageView)) //image

            findViewById<TextView>(R.id.productRatingTextView).text =
                it?.rating?.rate.toString()//rating
            findViewById<TextView>(R.id.productTitleTextView).text = it?.title //title
            findViewById<TextView>(R.id.productPriceTextView).text = "$ ${it?.price}" //price
            findViewById<TextView>(R.id.productDescriptionTextView).text =
                it?.description //description

            // check if product is in cart
            if (accountRepo.getCart().value?.contains(it) == true) {
                findViewById<AppCompatButton>(R.id.addToCartButton).text =
                    getString(R.string.remove_from_cart)
                isInCart = true
            } else {
                findViewById<AppCompatButton>(R.id.addToCartButton).text =
                    getString(R.string.add_to_cart)
                isInCart = false
            }

            //check if product is in favorite list
            if (accountRepo.getFavorite().value?.contains(it) == true) {
                findViewById<ImageView>(R.id.productFavoriteIcon).setImageResource(R.drawable.ic_baseline_favorite_48)
                isInFavorite = true
            } else {
                findViewById<ImageView>(R.id.productFavoriteIcon).setImageResource(R.drawable.ic_outline_favorite_border_48)
                isInFavorite = false
            }

            currentProduct = it!!
        }
    }

    override fun onClick(v: View?) {
        //todo: may clean clicks code and depend on vm
        when (v?.id) {
            R.id.addToCartButton -> {
                val button: AppCompatButton = v as AppCompatButton
                if (isInCart) {
                    accountRepo.removeFromCart(currentProduct)
                    Toast.makeText(v.context, "Item is removed from cart", Toast.LENGTH_LONG).show()
                    button.text = getString(R.string.add_to_cart)
                    isInCart = false
                } else {
                    accountRepo.addToCart(currentProduct)
                    Toast.makeText(v.context, "Item is added to cart", Toast.LENGTH_LONG).show()
                    button.text = getString(R.string.remove_from_cart)
                    isInCart = true
                }
            }
            R.id.productFavoriteIcon -> {
                if (isInFavorite) {
                    accountRepo.removeFromFavorite(currentProduct)
                    findViewById<ImageView>(R.id.productFavoriteIcon).setImageResource(R.drawable.ic_outline_favorite_border_48)
                    isInFavorite = false
                } else {
                    accountRepo.addToFavorite(currentProduct)
                    findViewById<ImageView>(R.id.productFavoriteIcon).setImageResource(R.drawable.ic_baseline_favorite_48)
                    isInFavorite = true
                }
            }
        }
    }
}