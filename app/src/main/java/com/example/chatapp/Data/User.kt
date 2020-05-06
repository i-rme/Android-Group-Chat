package com.example.chatapp.Data

class User (emp_name: String, emp_age : Int, emp_password: String ) {
    var Age: Int
    var username: String
    var password: String

    // initializer block
    init {
        Age = emp_age
        username = emp_name
        password= emp_password

    }
}