package com.myasser.wafrly.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.views.LoginActivity

class AccountRepository(val context: Context) : IAccountRepository {
    private val auth: FirebaseAuth = Firebase.auth

    override fun googleLogin(): MutableLiveData<Boolean> {
        //todo: read firebase doc, define a way to auth with google's token
        Toast.makeText(context, "Google login not implemented", Toast.LENGTH_LONG).show()
        return MutableLiveData(true)
    }

    override fun login(email: String, password: String): MutableLiveData<Boolean> {
        var loginSuccess = MutableLiveData<Boolean>()
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(context,
                    context.getString(R.string.login_successfully),
                    Toast.LENGTH_SHORT).show()
                LoginActivity.user = auth.currentUser
                loginSuccess = MutableLiveData(true)
            } else {
                loginSuccess = MutableLiveData(false)
            }
        }
        return loginSuccess
    }

    override fun register(email: String, password: String): MutableLiveData<Boolean> {
        var registerSuccess = MutableLiveData<Boolean>()
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                LoginActivity.user = auth.currentUser
                registerSuccess = MutableLiveData(true)
            } else {
                LoginActivity.user = null
                registerSuccess = MutableLiveData(false)
            }
        }
        return registerSuccess
    }

    override fun addToCart(product: Product) {
        TODO("Not yet implemented")
    }

    override fun addToFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeFromCart(product: Product) {
        TODO("Not yet implemented")
    }

    override fun removeFromFavorite(product: Product) {
        TODO("Not yet implemented")
    }

    override fun getCart(): MutableLiveData<List<Product>> {
        TODO("Not yet implemented")
    }

    override fun getFavorite(): MutableLiveData<List<Product>> {
        TODO("Not yet implemented")
    }
}