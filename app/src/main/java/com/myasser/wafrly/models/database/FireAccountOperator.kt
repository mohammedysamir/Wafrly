package com.myasser.wafrly.models.database

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.myasser.wafrly.R
import com.myasser.wafrly.models.data.Product
import com.myasser.wafrly.views.CategoryActivity
import com.myasser.wafrly.views.LoginActivity

class FireAccountOperator(val context: Context) : AccountOperators {
    private val auth: FirebaseAuth = Firebase.auth
    private val database = Firebase.database.getReference("wafrly")
    private val cartReference = database.child("cart")
    private val favoriteReference = database.child("favorite")

    override fun login(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                context.startActivity(Intent(context, CategoryActivity::class.java))
                Toast.makeText(context,
                    context.getString(R.string.login_successfully),
                    Toast.LENGTH_SHORT).show()
                LoginActivity.user = auth.currentUser
            } else {
                Toast.makeText(context,
                    context.getString(R.string.login_failed),
                    Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showGoogleLogin(): GoogleSignInClient {
        val webClientId = "836596930234-gn4b5h8td54c7av2e7164s2cqm6inkuv.apps.googleusercontent.com"
        val signInRequest = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(webClientId)
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(context, signInRequest)
    }

    override fun handleGoogleLogin(task: Task<GoogleSignInAccount>) {
        try {
            val account = task.getResult(ApiException::class.java)
            account?.let {
                //get credentials
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                auth.signInWithCredential(credential).addOnCompleteListener {
                    if (task.isSuccessful) {
                        context.startActivity(Intent(context, CategoryActivity::class.java))
                        LoginActivity.user = auth.currentUser
                    } else {
                        Toast.makeText(context,
                            context.getString(R.string.login_failed),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } catch (e: ApiException) {
            Log.e("Google Login", "signInResult:failed code=" + e.statusCode)
        }
    }

    override fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                context.startActivity(Intent(context, CategoryActivity::class.java))
                Toast.makeText(context,
                    context.getString(R.string.register_successfully),
                    Toast.LENGTH_SHORT).show()
                LoginActivity.user = auth.currentUser
            } else {
                Toast.makeText(context,
                    context.getString(R.string.register_failed),
                    Toast.LENGTH_SHORT).show()
            }
        }
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

    override fun getCurrentUser() = auth.currentUser

    override fun notifyPurchase(bill: Double) {
        val channelId = "100"
        val title = "Wafrly purchase confirmation"
        val body =
            "A purchase operations has been done with a bill of $ $bill. Thank you for using Wafrly"
        val builder = NotificationCompat.Builder(context, channelId).setSmallIcon(R.drawable.wafrly)
            .setContentTitle(title)
            .setContentText(body).setPriority(NotificationCompat.PRIORITY_HIGH).setStyle(
                NotificationCompat.BigTextStyle().bigText(body)) //more than one line body
            .setAutoCancel(true)
        //if we want to navigate to app's activity, we must define an explicit intent and pass it to the builder
        //create  notification channel
        createNotificationChannel(context.resources.getString(R.string.app_name), title, channelId)
        with(NotificationManagerCompat.from(context)) {
            notify(channelId.toInt(), builder.build())
        }
    }

    private fun createNotificationChannel(appName: String, title: String, channelId: String) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, appName, importance).apply {
            this.description = title
            this.enableLights(true)
        }
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}