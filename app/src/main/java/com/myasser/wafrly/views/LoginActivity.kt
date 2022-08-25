package com.myasser.wafrly.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.myasser.wafrly.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
//todo: determine if user is new or not ... or add Tab layout with [Register, Login] and switch between them.