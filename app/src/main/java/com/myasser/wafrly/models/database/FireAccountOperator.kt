package com.myasser.wafrly.models.database

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.views.LoginActivity

class FireAccountOperator(val context: Context) : AccountOperators {
    private val auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database.getReference("wafrly")
    private val cartReference = database.child("cart")
    private val favoriteReference = database.child("favorite")

    override fun login(email: String, password: String): Boolean {
        var loginSuccess = false
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context,
                    context.getString(R.string.login_successfully),
                    Toast.LENGTH_SHORT).show()
                LoginActivity.user = auth.currentUser
                loginSuccess = true
            } else {
                loginSuccess = false
            }
        }
        return loginSuccess
    }

    override fun googleLogin(): Boolean {
        //todo: read firebase doc, define a way to auth with google's token
        Toast.makeText(context, "Google login not implemented", Toast.LENGTH_LONG).show()
        return true
    }

    override fun register(email: String, password: String): Boolean {
        var registerSuccess = false
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                LoginActivity.user = auth.currentUser
                registerSuccess = true
            } else {
                LoginActivity.user = null
                registerSuccess = false
            }
        }
        return registerSuccess
    }

    //todo: test below functions
    override fun addToCart(product: Product) {
        cartReference.push().setValue(product)
    }

    override fun addToFavorite(product: Product) {
        favoriteReference.push().setValue(product)
    }

    override fun removeFromCart(product: Product) {
        cartReference.orderByChild("title").equalTo(product.title)
            .addListenerForSingleValueEvent(object :
                com.google.firebase.database.ValueEventListener {
                override fun onCancelled(p0: com.google.firebase.database.DatabaseError) {
                    Toast.makeText(context,
                        context.getString(R.string.remove_cart_error),
                        Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(p0: com.google.firebase.database.DataSnapshot) {
                    for (snapshot in p0.children) {
                        snapshot.ref.removeValue()
                    }
                }
            })
    }

    override fun removeFromFavorite(product: Product) {
        favoriteReference.orderByChild("title").equalTo(product.title)
            .addListenerForSingleValueEvent(object :
                com.google.firebase.database.ValueEventListener {
                override fun onCancelled(p0: com.google.firebase.database.DatabaseError) {
                    Toast.makeText(context,
                        context.getString(R.string.remove_favorite_error),
                        Toast.LENGTH_SHORT).show()
                }

                override fun onDataChange(p0: com.google.firebase.database.DataSnapshot) {
                    for (snapshot in p0.children) {
                        snapshot.ref.removeValue()
                    }
                }
            })
    }

    override fun getCart(): List<Product> {
        val products = ArrayList<Product>()
        cartReference.get().addOnSuccessListener {
            for (snapshot in it.children) {
                products.add(snapshot.getValue(Product::class.java)!!)
            }
        }
        return products
    }

    override fun clearCart() {
        //todo: may need to be tested
        cartReference.setValue(null)
    }

    override fun getFavorite(): List<Product> {
        val products = ArrayList<Product>()
        favoriteReference.get().addOnSuccessListener {
            for (snapshot in it.children) {
                products.add(snapshot.getValue(Product::class.java)!!)
            }
        }
        return products
    }
}