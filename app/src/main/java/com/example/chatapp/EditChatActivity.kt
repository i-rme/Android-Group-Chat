package com.example.chatapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapp.data.Chat
import com.example.chatapp.data.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_create_chat.*
import kotlinx.android.synthetic.main.activity_detail_chat.*
import kotlinx.android.synthetic.main.activity_edit_chat.*

class EditChatActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_chat)

        getActionBar()?.setTitle("Chat Edition");
        getSupportActionBar()?.setTitle("Chat Edition");  // provide compatibility

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
                        )
                    )
                }
                val userListAdapter = CustomArrayAdapterUser(this@EditChatActivity, R.layout.row_element, allUsers)
                lvEdit.adapter = userListAdapter
            }


        lvEdit.setOnItemClickListener { parent, view, position, _ ->
            lvEdit.getChildAt(position).setBackgroundColor(Color.GRAY)

            if (!chatUsers.contains(allUsers[position])) {
                chatUsers.add(allUsers[position])

//                val toast = Toast.makeText(
//                    applicationContext,
//                    "User: " + allUsers[position].username + " added",
//                    Toast.LENGTH_SHORT
//                )
//                toast.show()
            } else {
                val toast1 =
                    Toast.makeText(applicationContext, "User already added", Toast.LENGTH_SHORT)
                toast1.show()
            }


        }


        btnEdit.setOnClickListener(){
            var chat= Chat(etEditName.text.toString())
            val chatId = intent.getStringExtra("Chat_ID")
            ChatListProvider.updateChat(chat, chatUsers,chatId)
            finish()
        }
















    }
}
