package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.data.Chat
import com.example.chatapp.data.Message
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detail_chat.*
import kotlinx.android.synthetic.main.content_chat_list.*

class ChatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        val chatId = intent.getStringExtra("Chat_ID")


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_chat)

        val db = FirebaseDatabase.getInstance().getReference("messages")
        val eventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                ChatProvider.messageList.clear()
                for (postSnapshot in p0.children) {
                    Log.d("", postSnapshot.value.toString())
                    val message = postSnapshot.getValue(Message::class.java)
                    if (message != null) {
                        ChatProvider.addMessage(message)
                    }
                }
                val chatDetailAdapter = ChatDetailAdapter(this@ChatDetailActivity, ChatProvider.messageList)
                lvDetailChat.adapter = chatDetailAdapter

            }
            override fun onCancelled(p0: DatabaseError) {

            }

        }
        db.addValueEventListener(eventListener)

    }
}