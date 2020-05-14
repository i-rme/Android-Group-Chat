package com.example.chatapp.data

data class Message (
    var id: String = "",
    var user: User = User("", "" ,0),
    var text: String = "")
