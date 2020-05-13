package com.example.chatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ChatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_chat)

        val launchIntent = intent

        if (launchIntent != null) {
            val index = launchIntent.getIntExtra("Chat_ID", -1)
        }
    }
}
