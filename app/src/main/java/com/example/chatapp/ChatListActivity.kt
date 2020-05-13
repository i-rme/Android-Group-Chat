package com.example.chatapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.data.Chat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.content_chat_list.*

class ChatListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        //get Database
        var db = FirebaseDatabase.getInstance().getReference("chats")
        //check if chatlist is empty
        if(ChatListProvider.chatList.isEmpty()){
            val menuListener = object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val iter = dataSnapshot.children.iterator()
                    if (iter.hasNext()){
                        val chat = iter.next().getValue(Chat::class.java)
                        if (chat != null) {
                            ChatListProvider.addChat(chat)
                        }
                    }
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            db.addListenerForSingleValueEvent(menuListener)
        }

        val chatListAdapter = ChatListAdapter(this, ChatListProvider.chatList)
        lvChatList.adapter = chatListAdapter
    }


}
