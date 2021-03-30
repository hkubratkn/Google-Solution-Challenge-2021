package com.kapirti.googlesolutionchallenge_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.activity_second.*

class ProfileActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        auth= FirebaseAuth.getInstance()

        menu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.top_menu_profile,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.logout){
            auth.signOut()
            val intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
    fun waterProfile(view: View){
        Toast.makeText(this,"Bought water",Toast.LENGTH_LONG).show()
    }
    fun placeProfile(view:View){
        Toast.makeText(this,"Bought place",Toast.LENGTH_LONG).show()
    }
    fun forestProfile(view:View){
        Toast.makeText(this,"perpendicular to the forest",Toast.LENGTH_LONG).show()
    }
    fun menu(){
        bottomNavProfile.setOnNavigationItemSelectedListener{
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