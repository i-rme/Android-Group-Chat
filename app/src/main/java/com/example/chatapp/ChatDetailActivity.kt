package com.example.chatapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val chatId = intent.getStringExtra("Chat_ID");


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_chat)

        val toast = Toast.makeText(applicationContext, chatId, Toast.LENGTH_SHORT)
        toast.show()
    }
}
