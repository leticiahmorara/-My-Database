package com.example.myfirebaseddatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UsersActivity : AppCompatActivity() {
    var mListUser:ListView ?=null
    var users :ArrayList<User> ?=null
    var adapter:CustomAdapter ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users_avtivity)
        mListUser=findViewById(R.id.mListUsers)
        users= ArrayList()
        adapter= CustomAdapter(this,users!!)
        var ref =FirebaseDatabase.getInstance().getReference().child("Users")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                users!!.clear()
                for (snap in snapshot.children){
                    var user=snap.getValue(User::class.java)
                    users!!.add(user!!)
                }

            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Ask admin to open DB",
                                                              Toast.LENGTH_LONG).show()


            }
        })
        mListUser!!.adapter=adapter!!
        mListUser!!.setOnClickListener 
        }
    }
}