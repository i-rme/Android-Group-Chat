package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
//            etUsername.text
//            etPassword.text
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtra("key", "")
//            startActivity(intent)
        }
    }


}
