package com.myasser.wafrly.views

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.myasser.wafrly.R

class SplashActivity : AppCompatActivity() {
    init {
        FirebaseApp.initializeApp(this) //initialize firebase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //set animation to logo
        findViewById<ImageView>(R.id.wafrlyLogo).animation =
            android.view.animation.AnimationUtils.loadAnimation(this, R.anim.splash_anim)

        Handler(Looper.getMainLooper()).postDelayed({
            //todo: remove hard-coded delay and depend on max( coroutines department fetching, 2 seconds) -> Hook with Splash VM
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finish()
        }, 2000)
        //todo: image should be replaced with dark-version if the theme is dark
    }
}
/**
 * This project is a practice for:
 *  1. APIs using Retrofit2
 *  2. Coroutines
 *  3. Remote storage using Firebase (done)
 *  4. Auth management using Firebase (done)
 *  5. Notifications using Firebase or custom notifications (done/ custom)
 *  6. MVVM architecture (done)
 *  7. sharedPref to save local user's data
 *  8. DI using (koin, hilt, dagger)
 *  9. Github actions to publish update to store when a new release is merged to main
 *  phone verification: https://firebase.google.com/docs/auth/android/phone-auth?authuser=0&hl=en
 */
/*
* Thinking corner:
*   in MVVM we must obey this flow:
*   View -> ViewModel -> Repository -> Database/API
*   and to follow strategy pattern, FireAccountOperator must have the implementation of firebase storing and fetching code. then Repository will fetch data from it.
* */