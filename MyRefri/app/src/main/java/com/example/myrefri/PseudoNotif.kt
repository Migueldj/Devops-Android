package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PseudoNotif : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pseudo_notif)

        val button = findViewById<Button>(R.id.notif)

        button.setOnClickListener{
            val intent = Intent(this,Status::class.java)
            startActivity(intent)
        }
    }


}