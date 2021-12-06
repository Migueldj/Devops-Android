package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class StatusV2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status_v2)

        //Código para que se despliegue en statusV2 lo correspondiente a la ventana en main
        val comida1:String = intent.getStringExtra("Nivel1").toString()
        val refri1: TextView = findViewById(R.id.tv_cn1)
        refri1.text = comida1

        val comida2:String = intent.getStringExtra("Nivel2").toString()
        val refri2: TextView = findViewById(R.id.tv_cn2)
        refri2.text = comida2

        val comida3:String = intent.getStringExtra("Nivel3").toString()
        val refri3: TextView = findViewById(R.id.tv_cn3)
        refri3.text = comida3

        val comida4:String = intent.getStringExtra("Nivel4").toString()
        val refri4: TextView = findViewById(R.id.tv_cn4)
        refri4.text = comida1

        val comida5:String = intent.getStringExtra("Nivel5").toString()
        val refri5: TextView = findViewById(R.id.tv_cn5)
        refri5.text = comida5

        val comida6:String = intent.getStringExtra("Nivel6").toString()
        val refri6: TextView = findViewById(R.id.tv_cn6)
        refri6.text = comida6

        //Código para que al pulsar el botón pasa a la ventana correspondiente
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }

    }
}