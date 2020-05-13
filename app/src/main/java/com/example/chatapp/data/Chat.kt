package com.example.chatapp.data

data class Chat (var id: String? = "",
                 var chatName: String = "",
                 var users: MutableList<User> = mutableListOf<User>(),
                 var messages: MutableList<Message> = mutableListOf<Message>() )

