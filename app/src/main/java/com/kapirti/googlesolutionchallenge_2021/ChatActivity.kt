package com.kapirti.googlesolutionchallenge_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.controls.actions.ModeAction
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthSettings
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.activity_second.*

class ChatActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    private lateinit var database:FirebaseFirestore
    var loadedList=ArrayList<ModelForChat>()
    private lateinit var recyclerViewAdapter:ChatAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        auth= FirebaseAuth.getInstance()
        database= FirebaseFirestore.getInstance()

        takeFile()
        menu()

        val layoutManager=LinearLayoutManager(this)
        recyclerView.layoutManager=layoutManager
        recyclerViewAdapter=ChatAdapter(loadedList)
        recyclerView.adapter=recyclerViewAdapter
    }

    fun sentChatBtn(view: View){
        val currentUsersId=auth.currentUser.uid

        val text=editTextChat.text.toString()
        val time= Timestamp.now().toString()

        val askHashMap=hashMapOf<String,Any>()
        askHashMap.put("ask",text)
        askHashMap.put("time",time)

        database.collection(currentUsersId).add(askHashMap).addOnCompleteListener {
            if(it.isSuccessful){
                Toast.makeText(this,"well", Toast.LENGTH_LONG).show()
                editTextChat.setText("")
            }
        }.addOnFailureListener {
            Toast.makeText(this,it.localizedMessage,Toast.LENGTH_LONG).show()
        }
    }
    fun takeFile(){
        val currentUsersId=auth.currentUser.uid
        database.collection(currentUsersId).orderBy("time",Query.Direction.DESCENDING).addSnapshotListener{ snapshot, error ->
            if (error!=null){
                Toast.makeText(this,error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if (snapshot!=null){
                    if (!snapshot.isEmpty){
                        val documents=snapshot.documents
                        loadedList.clear()
                        for (document in documents){
                            val textChat=document.get("ask") as String
                            val timeChat=document.get("time") as String

                            var loaded=ModelForChat(textChat,timeChat)
                            loadedList.add(loaded)

                        }

                    }
                }
            }

        }
    }


    fun menu(){
        bottomNavChat.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.home ->{
                    val intent= Intent(this,SecondActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.chat ->{
                    val intent= Intent(this,ChatActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.profile -> {
                    val intent= Intent(this,ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

}