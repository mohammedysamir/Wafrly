package com.myasser.wafrly.views

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
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
            if (isChecked) {
                findViewById<AppCompatButton>(R.id.registerButton).text = getString(R.string.login)
                findViewById<AppCompatButton>(R.id.loginGoogleButton).visibility = View.VISIBLE
            } else {
                findViewById<AppCompatButton>(R.id.registerButton).text =
                    getString(R.string.register)
                findViewById<AppCompatButton>(R.id.loginGoogleButton).visibility = View.INVISIBLE
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
                //input validation non-empty email and password
                if (email.isEmpty()) {
                    emailEditText.error = getString(R.string.empty_email_error)
                    Toast.makeText(this,
                        getString(R.string.fill_fields_command),
                        Toast.LENGTH_SHORT).show()
                }
                if (password.isEmpty()) {
                    passwordEditText.error = getString(R.string.empty_password_error)
                    Toast.makeText(this,
                        getString(R.string.fill_fields_command),
                        Toast.LENGTH_SHORT).show()
                }

                if (isChecked) {
                    //login validation
                    loginViewModel.login(email, password).observe(this) {
                        if (it) {
                            startActivity(Intent(this, CategoryActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this,
                                getString(R.string.login_failed),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    //register
                    loginViewModel.register(email, password).observe(this) {
                        if (it) {
                            Toast.makeText(v.context,
                                getString(R.string.register_successfully),
                                Toast.LENGTH_SHORT).show()
                            //navigate to display categories activity
                            startActivity(Intent(this, CategoryActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(v.context,
                                getString(R.string.register_failed),
                                Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
            R.id.loginGoogleButton -> {
                //login with google (firebase)
                loginViewModel.showGoogleLogin().observe(this){
                    startActivityForResult(it,googleSignInRequestCode)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            googleSignInRequestCode -> {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
                loginViewModel.handleGoogleLogin(task).observe(this){
                    if(it){
                        startActivity(Intent(this, CategoryActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this,
                            getString(R.string.login_failed),
                            Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}