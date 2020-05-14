package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.chatapp.data.Chat
import com.example.chatapp.data.Message
import com.example.chatapp.data.User
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.etPassword
import kotlinx.android.synthetic.main.activity_login.etUsername
import kotlinx.android.synthetic.main.activity_register.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
        login(etUsername.text.toString(), etPassword.text.toString())
        }

        val username : EditText = etUsername
        val password : EditText = etPassword

        val loginButton : Button = findViewById(R.id.btnLogin)

        loginButton.setOnClickListener {
            login(username.text.toString(), password.text.toString())

        }

        btnRegister.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun login(username: String, password: String) {

        val db = Firebase.firestore

        db.collection("users").document(username).get()
            .addOnSuccessListener { document ->

                    if(document.data?.get("username") == username && document.data?.get("password") == password){
                        val intent = Intent(this, ChatListActivity::class.java)
                        intent.putExtra("user", username)
                        startActivity(intent)
                    }else{
                        val toast = Toast.makeText(applicationContext, "Incorrect username or password", Toast.LENGTH_SHORT)
                        toast.show()
                    }
            }
    }

}
