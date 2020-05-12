package com.example.chatapp

import com.example.chatapp.data.Message
import com.google.firebase.database.FirebaseDatabase

object ChatProvider {

    fun postMessage(chatId: Int, message: Message) {

        val database = FirebaseDatabase.getInstance().reference
        database.child("chats").child(chatId.toString()).child(message.id.toString()).setValue(message)

        /*
            USAGE
            
            var testUser = User("TestUsername", "password", 43)
            var testMessage = Message(1, testUser, "This is my first message")
            ChatProvider.postMessage(1, testMessage)

         */
    }

}