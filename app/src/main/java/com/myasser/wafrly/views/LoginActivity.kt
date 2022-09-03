package com.myasser.wafrly.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.AlphaAnimation
import android.view.animation.DecelerateInterpolator
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.myasser.wafrly.R
import com.myasser.wafrly.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var checkBox: CheckBox
    private var isChecked = false //indicator for login-register checkbox
    private lateinit var loginViewModel: LoginViewModel
    private val googleSignInRequestCode = 1900

    companion object {
        var user: FirebaseUser? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //define view model
        loginViewModel = LoginViewModel(this)
        //checkbox
        checkBox = findViewById(R.id.loginCheckBox)
        //set checked listener
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            //when the box is checked ... make the username edit text invisible and the login with google button visible, change register button label
            this.isChecked = isChecked
            val loginGoogleButton = findViewById<AppCompatButton>(R.id.loginGoogleButton)
            if (isChecked) {
                findViewById<AppCompatButton>(R.id.registerButton).text = getString(R.string.login)
                //animate google button
                loginGoogleButton.animation = AlphaAnimation(0f, 1f).apply { //fade in anim
                    interpolator = DecelerateInterpolator()
                    duration = 500
                    fillAfter = true
                }
            } else {
                findViewById<AppCompatButton>(R.id.registerButton).text =
                    getString(R.string.register)
                //fade away google button
                loginGoogleButton.animation = AlphaAnimation(1f, 0f).apply { //fade out
                    interpolator = AccelerateInterpolator()
                    duration = 500
                    fillAfter = true
                }
            }
        }

        //add listeners
        findViewById<AppCompatButton>(R.id.loginGoogleButton).setOnClickListener(this)
        findViewById<AppCompatButton>(R.id.registerButton).setOnClickListener(this)
    }

    //may need to check current user in OnStart
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.registerButton -> {
                val emailEditText = findViewById<EditText>(R.id.userEmailEditText)
                val passwordEditText = findViewById<EditText>(R.id.userPasswordEditText)

                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                var valid = true
                //input validation non-empty email and password
                if (email.isEmpty()) {
                    emailEditText.error = getString(R.string.empty_email_error)
                    Toast.makeText(this,
                        getString(R.string.fill_fields_command),
                        Toast.LENGTH_SHORT).show()
                    valid = false
                }
                if (!email.contains("@") || !email.contains(".com")) {
                    emailEditText.error = getString(R.string.invalid_email_format)
                    valid = false
                }
                if (password.isEmpty()) {
                    passwordEditText.error = getString(R.string.empty_password_error)
                    Toast.makeText(this,
                        getString(R.string.fill_fields_command),
                        Toast.LENGTH_SHORT).show()
                    valid = false
                }

                if (valid) {
                    if (isChecked) {
                        //login validation
                        loginViewModel.login(email, password)
//                        finish()
                    } else {
                        //register
                        loginViewModel.register(email, password)
//                        finish()
                    }
                }
            }
            R.id.loginGoogleButton -> {
                //login with google (firebase)
                loginViewModel.showGoogleLogin().observe(this) {
                    startActivityForResult(it, googleSignInRequestCode)
//                    finish()
                }
            }
        }
    }
    //todo: retype finish() but after solving listener problem
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            googleSignInRequestCode -> {
                val task: Task<GoogleSignInAccount> =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
                loginViewModel.handleGoogleLogin(task)
            }
        }
    }
}