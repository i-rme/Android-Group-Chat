package com.example.chatapp.provider

import com.example.chatapp.data.Message
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Chat provider to show the chat
 * and get the it from the database
 */

object ChatProvider {

    var messageList = mutableListOf<Message>()
    var userList = mutableListOf<User>()

    fun postMessage(chatId: String, message: Message) {

        val database = FirebaseDatabase.getInstance().reference
        database.child("messages").child(chatId).push().setValue(message)
        addMessage(message)

    }


    fun addUser(user: User) {
        userList.add(user)
    }

    fun addMessage(message: Message) {
        messageList.add(message)
    }

    fun removeUser(username: String, chatId: String) {

        val database: DatabaseReference = FirebaseDatabase.getInstance().reference
        database.child("chats").child(chatId).child("users").child(username).removeValue();

    }

}