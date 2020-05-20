package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        getActionBar()?.setTitle("Registration");
        getSupportActionBar()?.setTitle("Registration");  // provide compatibility

        btnSubmit.setOnClickListener() {
            register(etUsername.text.toString(), etPassword.text.toString(), etAge.text.toString().toInt())
            finish()
        }

    }

    fun register(username: String, password: String, age: Int): Boolean {

        if(true){

            var user = User(username, password, age)
            val db = Firebase.firestore
            val users = db.collection("users")
            users.document(user.username.toString()).set(user)


            val toast = Toast.makeText(applicationContext, "New user registered", Toast.LENGTH_SHORT)
            toast.show()

            return true
        }else{
            val toast = Toast.makeText(applicationContext, "Incorrect username or password", Toast.LENGTH_SHORT)
            toast.show()

            return false
        }

    }
}
