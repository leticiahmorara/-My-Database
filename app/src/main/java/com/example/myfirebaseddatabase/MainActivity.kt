package com.example.myfirebaseddatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    var mEdtName:EditText ?=null
    var mEdtEmail:EditText ?=null
    var mEdtIdNumber:EditText ?=null
    var mbtnSave:Button ?=null
    var mbtnView:Button ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mEdtName=findViewById(R.id.mEdtName)
        mEdtEmail=findViewById(R.id.mEdtEmail)
        mEdtIdNumber=findViewById(R.id.mEdtIdNumber)
        mbtnSave=findViewById(R.id.mbtnSave)
        mbtnView=findViewById(R.id.mbtnView)

        mbtnSave!!.setOnClickListener {
            var name=mEdtName!!.text.toString().trim()
            var email=mEdtEmail!!.text.toString().trim()
            var idNumber=mEdtIdNumber!!.text.toString().trim()
            if (name.isEmpty()){
                mEdtName!!.setError("Please fill this input")
                mEdtName!!.requestFocus()
            }else if (email.isEmpty()){
                mEdtEmail!!.setError("Please fill this input")
                mEdtEmail!!.requestFocus()
            }else if (idNumber.isEmpty()){
                mEdtIdNumber!!.setError("Please fill this input")
                mEdtIdNumber!!.requestFocus()
            }else{
                var time=System.currentTimeMillis().toString()
                var userData =User(name,email,idNumber,time)
               var ref=FirebaseDatabase.getInstance().getReference().child("Users/$time")
                ref.setValue(userData).addOnCompleteListener (){
                    task->
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"User saved successfully",
                        Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(applicationContext,"Saving user failed",
                        Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        mbtnView!!.setOnClickListener {
    startActivity(Intent(this,UsersActivity::class.java))
        }

    }
}