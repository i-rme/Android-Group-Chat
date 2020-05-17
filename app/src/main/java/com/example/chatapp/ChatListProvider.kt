package com.example.chatapp

import com.example.chatapp.data.Chat
import com.example.chatapp.data.Message
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


object ChatListProvider{
    private lateinit var database: DatabaseReference
    var chatList = mutableListOf<Chat>()
    lateinit var username: String

    fun postChat(chat: Chat) {

        val database = FirebaseDatabase.getInstance().reference
        database.child("chats").push().setValue(chat)

        /*
            USAGE

            var chat = Chat("ChatNameOne")
            ChatListProvider.postChat(chat)

         */
    }

    fun createChat(chatName: String, user: User){
        ChatProvider.addUser(user)
        chatList.add(Chat("idhere", chatName, ChatProvider.userList, ChatProvider.messageList))
    }
    fun addChat(chat: Chat){
        chatList.add(chat)
    }

}