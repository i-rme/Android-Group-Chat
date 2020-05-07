package com.example.chatapp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_login.*



class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
        login(etUsername.text.toString(), etPassword.text.toString())
        }



        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("message")



        // Read from the database
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) { // This method is called once with the initial value and again
// whenever data at this location is updated.
                val value =
                    dataSnapshot.getValue(String::class.java)!!
                Log.d("", "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) { // Failed to read value
                Log.w("",  "Failed to read value.", error.toException())
            }
        })

    }

    fun login(username: String?, password: String?): Boolean {

//        if(ContentProvider.login(User)){
        if(true){
//            val intent = Intent(this@MainActivity, SecondActivity::class.java)
//            intent.putExtra("key", "")
//            startActivity(intent)
            return true
        }else{
            val toast = Toast.makeText(applicationContext, "Incorrect username or password", Toast.LENGTH_SHORT)
            toast.show()
            return false
        }

    }



}
