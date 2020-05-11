package com.example.chatapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapp.data.CustomArrayAdapterUser
import com.example.chatapp.data.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_chat.*

class CreateChat : AppCompatActivity() {


    lateinit var adapter : CustomArrayAdapterUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_chat)



        val db = Firebase.firestore

        val dbUsers= mutableListOf<User>()

        val usersNewChat = arrayListOf<User>()

        db.collection("users").get()
            .addOnSuccessListener { result ->
                for (document in result) {

                    dbUsers.add(document as User)

                }
            }









        setContentView(R.layout.activity_create_chat)
        adapter = CustomArrayAdapterUser(context = this, resourceId = R.layout.row_element, items = dbUsers)
        lvUserNewChat.adapter = this.adapter


        lvUserNewChat.setOnItemClickListener { parent, view, position, _ ->


            usersNewChat.add(dbUsers[position])


            val toast = Toast.makeText(applicationContext, "User: "+dbUsers[position].username+" added", Toast.LENGTH_SHORT)
            toast.show()
        }





    }
}
