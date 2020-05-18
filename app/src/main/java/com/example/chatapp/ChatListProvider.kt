package com.example.chatapp

import android.util.Log
import android.widget.Toast
import com.example.chatapp.data.Chat
import com.example.chatapp.data.Message
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


object ChatListProvider{
    private lateinit var database: DatabaseReference
    var chatList = mutableListOf<Chat>()
    lateinit var username: String

    fun postChat(chat: Chat, userlist: MutableList<User>) {

        val database = FirebaseDatabase.getInstance().reference
        var pushedRef = database.child("chats").push()
        pushedRef.setValue(chat)

        var stringlist = mutableListOf<String>();
        for (user in userlist) {
            stringlist.add(user.username.toString());
        }
        chatList.add(chat)
        database.child("users").child(pushedRef.getKey().toString()).setValue(stringlist)

    }

    fun createChat(chatName: String, user: User){
        //ChatProvider.addUser(user)
        //chatList.add(Chat("idhere", chatName, ChatProvider.userList, ChatProvider.messageList))
    }
    fun addChat(chat: Chat){
        chatList.add(chat)
    }

}