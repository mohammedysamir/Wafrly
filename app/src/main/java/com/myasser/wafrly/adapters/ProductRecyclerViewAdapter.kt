package com.myasser.wafrly.adapters

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.models.database.AccountOperators
import com.myasser.wafrly.models.database.FireAccountOperator
import com.myasser.wafrly.views.ProductActivity

class ProductRecyclerViewAdapter(private val products: List<Product>, val context: Context) :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.ProductViewHolder>() {
    private val accountRepo: AccountOperators = FireAccountOperator(context)

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val productTitleTextView: TextView = view.findViewById(R.id.productTitle)
        val productPriceTextView: TextView = view.findViewById(R.id.productPrice)
        val productImageView: ImageView = view.findViewById(R.id.productImage)
        val productFavoriteIcon: ImageView = view.findViewById(R.id.favoriteIcon)
        val productCartIcon: ImageView = view.findViewById(R.id.addToCartIcon)
        val productImageCardView: CardView = view.findViewById(R.id.imageCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        //todo: find a better way to hook listeners here
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        val favoriteIcon = holder.productFavoriteIcon
        val cartIcon = holder.productCartIcon

        //set product properties
        holder.productTitleTextView.text = product.title
        holder.productPriceTextView.text = product.price.toString()
        Glide.with(holder.itemView.context).load(product.image).into(holder.productImageView)

        //change card color randomly
        val backgroundColor = colorsList.random()
        holder.productImageCardView.setCardBackgroundColor(ColorStateList.valueOf(backgroundColor))

        //todo: test icon switching
        holder.itemView.findViewById<ImageView>(R.id.addToCartIcon).setOnClickListener {
            if (accountRepo.getCart().contains(product)) {
                //remove it from db
                accountRepo.removeFromCart(product)
                //change image to add to cart
                cartIcon.setImageResource(R.drawable.ic_baseline_add_shopping_cart_36)
            } else {
                accountRepo.addToCart(product)
                cartIcon.setImageResource(R.drawable.ic_baseline_remove_shopping_cart_24)
            }
        }
        holder.itemView.findViewById<ImageView>(R.id.favoriteIcon).setOnClickListener {
            if (accountRepo.getFavorite().contains(product)) {
                accountRepo.removeFromFavorite(product)
                favoriteIcon.setImageResource(R.drawable.ic_outline_favorite_border_48)
            } else {
                accountRepo.addToFavorite(product)
                favoriteIcon.setImageResource(R.drawable.ic_baseline_favorite_48)
            }
        }
        holder.itemView.setOnClickListener {
            //todo: test if this will enable the product to be clicked
            //navigate and send product id
            context.startActivity(Intent(context, ProductActivity::class.java).putExtra("product id",
                product.id))
        }
    }

    override fun getItemCount() = products.size
}