package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnSubmit.setOnClickListener() {

           /* val intent = Intent(this, DoRegister::class.java)
            intent.putExtra("user", user1)
            startActivity(intent)*/
            register(etUsername.text.toString(), etPassword.text.toString(), etAge.text.toString().toInt())

        }

    }

    fun register(username: String, password: String, age: Int): Boolean {

        if(true){
            database = FirebaseDatabase.getInstance().reference

            var user = User(username, password, age)
            database.child("users").child(user.username!!).setValue(user)

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
