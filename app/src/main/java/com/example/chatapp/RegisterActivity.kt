package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)




        //var user1= User(etUsername.text.toString(),etAge.text.toString().toInt(),etPassword.text.toString())



        btnSubmit.setOnClickListener() {

           /* val intent = Intent(this, DoRegister::class.java)
            var user1= User(etUsername.text.toString(),etAge.text.toString().toInt(),etPassword.text.toString())
            intent.putExtra("user", user1)
            startActivity(intent)*/
        }




    }
}
