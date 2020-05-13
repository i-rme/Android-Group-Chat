package com.example.chatapp

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.data.Chat
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase

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
        val db = FirebaseDatabase.getInstance().getReference("chats")
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
                    val chatListAdapter = ChatListAdapter(this@ChatListActivity, ChatListProvider.chatList)
                    lvChatList.adapter = chatListAdapter
                }
                override fun onCancelled(databaseError: DatabaseError) {
                    // handle error
                }
            }
            db.addValueEventListener(menuListener)

        }else{
            val eventListener = object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {
                    val iter = p0.children.iterator()
                    if (iter.hasNext()){
                        val chat = iter.next().getValue(Chat::class.java)
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
        }


    }


}
