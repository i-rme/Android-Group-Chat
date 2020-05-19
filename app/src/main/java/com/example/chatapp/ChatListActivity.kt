package com.example.chatapp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.chatapp.data.Chat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_chat_list.*
import kotlinx.android.synthetic.main.content_chat_list.*
import kotlin.random.Random

class ChatListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            val intent = Intent(this, CreateChatActivity::class.java)
            startActivity(intent)
        }

        val db = FirebaseDatabase.getInstance().getReference("chats")
            val eventListener = object : ValueEventListener{
                override fun onDataChange(p0: DataSnapshot) {

                    ChatListProvider.chatList.clear()
                    for (postSnapshot in p0.children) {
                        var chat = postSnapshot.getValue(Chat::class.java)
                        if (chat != null && chat.users.containsKey(ChatListProvider.username)) {
                            chat.id = postSnapshot.key.toString();
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
            val intent = Intent(this, ChatDetailActivity::class.java)
            intent.putExtra("Chat_ID", ChatListProvider.chatList.get(position).id)
            startActivity(intent)
        }



        lvChatList.setOnItemLongClickListener{ parent, view, position, _ ->
            val intent = Intent(this, EditChatActivity::class.java)
            intent.putExtra("Chat_ID", ChatListProvider.chatList.get(position).id)
            startActivity(intent)
            return@setOnItemLongClickListener true
        }




        // NOTIFICATIONS




        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Messages"
            val descriptionText = "Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("Messages", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }



        var i = 0;
        val dbMessages = FirebaseDatabase.getInstance().getReference("messages")
        val eventListenerDbMessages = object : ValueEventListener{
            override fun onDataChange(p0: DataSnapshot) {
                if (i++ != 0 && !ChatListProvider.CHATTING) {
                    displayNotification("ChatApp", "You may have new messages.")
                }
            }
            override fun onCancelled(p0: DatabaseError) {
            }
        }
        dbMessages.addValueEventListener(eventListenerDbMessages)








    }

    fun displayNotification(title: String, text: String) {
        var builder = NotificationCompat.Builder(this, "Messages")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(text)
            .setPriority(NotificationCompat.PRIORITY_MAX)

        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(123, builder.build())
        }
    }

}
