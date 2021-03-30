package com.kapirti.googlesolutionchallenge_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()
        val currentUser=auth.currentUser
        if(currentUser!=null){
            val intent= Intent(this,SecondActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    fun signin(view:View){
        val email=editEmail.text.toString()
        val password=editPassword.text.toString()
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                val intent=Intent(this,SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    fun login(view:View){
        val email=editEmail.text.toString()
        val password=editPassword.text.toString()
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
            if(it.isSuccessful){
                val intent=Intent(this,SecondActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
}