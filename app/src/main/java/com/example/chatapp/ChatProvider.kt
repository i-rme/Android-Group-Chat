package com.example.chatapp

import android.util.Log
import android.widget.Toast
import com.example.chatapp.data.Message
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


object ChatProvider {

    var messageList = mutableListOf<Message>()
    var userList = mutableListOf<User>()

    fun postMessage(chatId: String, message: Message) {

        val database = FirebaseDatabase.getInstance().reference
        database.child("messages").child(chatId).push().setValue(message)
        addMessage(message)
        /*
            USAGE

            var message = Message("userstring", "textinside")
            ChatProvider.postMessage("chatidGoesHere213", message)

         */
    }


    fun addUser(user: User){
        userList.add(user)
    }
    fun addMessage(message: Message){
        messageList.add(message)
    }
    fun deleteChat(){

    }
    fun removeUser(username: String, chatId: String){
        val database: DatabaseReference =FirebaseDatabase.getInstance().reference
        database.child("chats").child(chatId).child("users").child(username).removeValue();
    }

}