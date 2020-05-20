package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.provider.ChatListProvider
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.etUsername

/**
 * An activity for login in the chat
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        ChatListProvider.CHATTING = false

        btnLogin.setOnClickListener {
            login(etUsername.text.toString(), etPassword.text.toString())
        }

        val username: EditText = etUsername
        val password: EditText = etPassword

        val loginButton: Button = findViewById(R.id.btnLogin)

        loginButton.setOnClickListener {
            login(username.text.toString(), password.text.toString())
        }

        btnRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun login(username: String, password: String) {

        val db = Firebase.firestore

        db.collection("users").document(username).get()
            .addOnSuccessListener { document ->

                if (document.data?.get("username") == username && document.data?.get("password") == password) {
                    ChatListProvider.username = username
                    val intent = Intent(this, ChatListActivity::class.java)
                    intent.putExtra("user", username)
                    startActivity(intent)
                } else {
                    val toast = Toast.makeText(
                        applicationContext,
                        "Incorrect username or password",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            }
    }

    override fun onResume() {
        super.onResume()
        ChatListProvider.CHATTING = false
    }

}
