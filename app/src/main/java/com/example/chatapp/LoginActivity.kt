package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            login(etUsername.text.toString(), etPassword.text.toString())
        }

    }

    fun login(username: String?, password: String?): Boolean {

//        if(ContentProvider.login(User)){
        if(true){
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtra("key", "")
//            startActivity(intent)
        }else{
            val toast = Toast.makeText(applicationContext, "Incorrect username or password", Toast.LENGTH_SHORT)
            toast.show()
        }
        return true
    }
    
}
