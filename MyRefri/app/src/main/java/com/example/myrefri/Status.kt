package com.example.myrefri

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Status : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    var p_carne: Int = 100
    var p_paleta: Int = 100
    var p_leche: Int = 100
    var p_salchicha: Int = 100
    var p_queso: Int = 100

    var sp_carne: String =""
    var sp_paleta: String = ""
    var sp_leche: String = ""
    var sp_salchicha: String = ""
    var sp_queso: String = ""

    val outputNv1 = findViewById<TextView>(R.id.txtNv1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,
            Context.MODE_PRIVATE)

        val sharedNv1 = sharedPreferences.getString("comidaNv1","")
        outputNv1.setText(sharedNv1).toString()

        val button = findViewById<Button>(R.id.btn_lista)
        button.setOnClickListener{
            val intent = Intent(this,ShoppingList::class.java)
            startActivity(intent)
        }



    }
}