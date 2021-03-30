package com.kapirti.googlesolutionchallenge_2021

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        menu()
    }
    fun menu(){
        bottomNav.setOnNavigationItemSelectedListener{
            when (it.itemId) {
                R.id.home ->{
                    val intent=Intent(this,SecondActivity::class.java)
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
                    val intent=Intent(this,ProfileActivity::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.top_menu_second,menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.water){
            Toast.makeText(this,"added water",Toast.LENGTH_LONG).show()
        } else if(item.itemId==R.id.grow){
            Toast.makeText(this,"growed",Toast.LENGTH_LONG).show()
        }
        return super.onOptionsItemSelected(item)
    }

}