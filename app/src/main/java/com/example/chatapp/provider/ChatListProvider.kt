package com.example.chatapp.provider

import com.example.chatapp.data.Chat
import com.example.chatapp.data.User
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

/**
 * Chat list provider to get the
 * information from the database
 * and save it here
 */
object ChatListProvider {
    private lateinit var database: DatabaseReference
    var chatList = mutableListOf<Chat>()
    lateinit var username: String
    var CHATTING = true;

    fun postChat(chat: Chat, userlist: MutableList<User>) {

        val database = FirebaseDatabase.getInstance().reference
        var pushedRef = database.child("chats").push()
        pushedRef.setValue(chat)

        val stringMap = mutableMapOf<String, String>()

        for (user in userlist) {
            stringMap.put(user.username.toString(), "")
        }
        chatList.add(chat)
        database.child("chats").child(pushedRef.getKey().toString()).child("users")
            .setValue(stringMap)

    }

    fun addChat(chat: Chat) {
        chatList.add(chat)
    }

    fun updateChat(chat: Chat, userlist: MutableList<User>, chatid: String) {

        val database = FirebaseDatabase.getInstance().reference
        var pushedRef = database.child("chats").child(chatid)
        pushedRef.setValue(chat)

        val stringMap = mutableMapOf<String, String>()

        for (user in userlist) {
            stringMap.put(user.username.toString(), "")
        }
        chatList.add(chat)
        database.child("chats").child(pushedRef.getKey().toString()).child("users")
            .setValue(stringMap)

    }
}