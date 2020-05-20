package com.example.chatapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.chatapp.adapter.ChatDetailAdapter
import com.example.chatapp.data.Message
import com.example.chatapp.provider.ChatListProvider
import com.example.chatapp.provider.ChatProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_detail_chat.*

/**
 * The detail chat is used for seeing the messages from a chat
 *
 */
class ChatDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        ChatListProvider.CHATTING = true

        val chatId = intent.getStringExtra("Chat_ID")
        val chatName = intent.getStringExtra("Chat_Name")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_chat)
        // get the from the databse messages
        val db = FirebaseDatabase.getInstance().getReference("messages").child(chatId)

        // make an listener for data changes
        val eventListener = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {

                getActionBar()?.setTitle(chatName);
                getSupportActionBar()?.setTitle(chatName);  // provide compatibility

                ChatProvider.messageList.clear()
                for (postSnapshot in p0.children) {
                    val messageMap = postSnapshot.getValue() as Map<String, String>
                    val message = Message(
                        messageMap.get("user").toString(),
                        messageMap.get("text").toString()
                    )
                    if (message != null) {
                        ChatProvider.addMessage(message)
                    }
                }
                val chatDetailAdapter =
                    ChatDetailAdapter(
                        this@ChatDetailActivity,
                        ChatProvider.messageList
                    )
                lvDetailChat.adapter = chatDetailAdapter

            }

            override fun onCancelled(p0: DatabaseError) {

            }

        }
        db.addValueEventListener(eventListener)

        btnSend.setOnClickListener {
            if (etSendMessage.text.toString() != "") {
                ChatProvider.postMessage(
                    chatId,
                    Message(ChatListProvider.username, etSendMessage.text.toString())
                )
                etSendMessage.setText("")
            } else {
                val toast = Toast.makeText(
                    applicationContext,
                    "Write something before sending.",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }

        btnBack.setOnClickListener {
            finish()
        }

        btnLeave.setOnClickListener {
            ChatProvider.removeUser(ChatListProvider.username, chatId)
            val toast =
                Toast.makeText(applicationContext, "You have left the chat.", Toast.LENGTH_SHORT)
            toast.show()
            finish()
        }
    }

    override fun onPause() {
        super.onPause()
        ChatListProvider.CHATTING = false
    }

    override fun onResume() {
        super.onResume()
        ChatListProvider.CHATTING = true
    }
}