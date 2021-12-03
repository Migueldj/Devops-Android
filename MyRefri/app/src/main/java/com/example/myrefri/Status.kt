package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class Status : AppCompatActivity() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        val comida1:String = intent.getStringExtra("Nivel1").toString()
        val refri1:TextView= findViewById(R.id.textViewNv1)
        refri1.text = comida1

        val button = findViewById<Button>(R.id.btn_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }



    }
}