package com.example.chatapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.data.Chat
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.content_chat_list.*

class ChatListActivity : AppCompatActivity() {
    private var chatListAdapter = ChatListAdapter(this, ChatListProvider.chatList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //get Database
        val db = FirebaseDatabase.getInstance().getReference("chats")
            val eventListener = object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {

                    ChatListProvider.chatList.clear()
                    for (postSnapshot in p0.children) {
                        val chat = postSnapshot.getValue(Chat::class.java)
                        if (chat != null) {
                            ChatListProvider.addChat(chat)
                        }
                    }
                    val chatListAdapter = ChatListAdapter(this@ChatListActivity, ChatListProvider.chatList)
                    lvChatList.adapter = chatListAdapter

                }
                override fun onCancelled(p0: DatabaseError) {

                }

            }
            db.addValueEventListener(eventListener)

        lvChatList.setOnItemClickListener{parent, view, position, id ->
            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("Chat_ID", id)
            startActivity(intent)
        }

    }


}
