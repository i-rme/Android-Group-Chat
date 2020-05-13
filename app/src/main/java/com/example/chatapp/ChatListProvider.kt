package com.example.chatapp

import com.example.chatapp.data.Chat
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference


object ChatListProvider{
    private lateinit var database: DatabaseReference
    var chatList = mutableListOf<Chat>()



    fun createChat(chatName: String, user: User){
        ChatProvider.addUser(user)
        chatList.add(Chat(database.key, chatName, ChatProvider.userList, ChatProvider.messageList))
    }
    fun addChat(chat: Chat){
        chatList.add(chat)
    }

}