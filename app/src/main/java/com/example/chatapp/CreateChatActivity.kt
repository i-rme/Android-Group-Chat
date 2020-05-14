package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chatapp.data.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_chat.*

class CreateChatActivity : AppCompatActivity() {

    lateinit var adapter: CustomArrayAdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_chat)

        val db = Firebase.firestore
        val allUsers = mutableListOf<User>()
        val chatUsers = mutableListOf<User>()


        db.collection("users").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    allUsers.add(
                        User(
                            document.data["username"].toString(),
                            document.data["password"].toString(),
                            (document.data["age"] as Long).toInt()
                        ))
                }
                val userListAdapter = CustomArrayAdapterUser(
                    this@CreateChatActivity,
                    R.layout.row_element,
                    allUsers
                )
                lvUsersOfNewChat.adapter = userListAdapter
            }

//        lvUsersOfNewChat.setOnItemClickListener { parent, view, position, _ ->
//
//            if(!chatUsers.contains(allUsers[position])) {
//                chatUsers.add(allUsers[position])
//
//                val toast = Toast.makeText(applicationContext, "User: " + allUsers[position].username + " added", Toast.LENGTH_SHORT)
//                toast.show()
//            }else{
//                val toast1 = Toast.makeText(applicationContext, "User already added", Toast.LENGTH_SHORT)
//                toast1.show()
//            }
//        }



    }


}
