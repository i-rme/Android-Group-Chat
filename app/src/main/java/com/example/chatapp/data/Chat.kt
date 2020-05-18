package com.example.chatapp.data

data class Chat (
                 var chatName: String = "",
                 var id: String = "",
                 var users: MutableMap<String, String> = mutableMapOf<String, String>(),
                 var messages: MutableList<Message> = mutableListOf<Message>() )

