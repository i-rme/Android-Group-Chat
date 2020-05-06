package com.example.chatapp.Data

class User (emp_name: String, emp_age : Int, emp_password: String ) {
    var age: Int
    var username: String
    var password: String

    // initializer block
    init {
        age = emp_age
        username = emp_name
        password= emp_password

    }
}