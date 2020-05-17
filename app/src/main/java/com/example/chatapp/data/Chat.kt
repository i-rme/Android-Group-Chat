package com.example.chatapp.data

data class Chat (
                 var chatName: String = "",
                 var id: String = "",
                 var users: MutableList<User> = mutableListOf<User>(),
                 var messages: MutableList<Message> = mutableListOf<Message>() )

