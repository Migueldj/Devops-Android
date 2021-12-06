package com.example.myrefri

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text


class Status : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

    var porc_n1: Int = 100
    var porc_n2: Int = 100
    var porc_n3: Int = 100
    var porc_n4: Int = 100
    var porc_n5: Int = 100


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

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
        //------------------------------------------------------------------------------------------/
        //Seleccionar imágen dependiendo de la selección del usuario
        val imv_n1: ImageView =findViewById(R.id.iv_n1)
        val imv_n2: ImageView =findViewById(R.id.iv_n2)
        val imv_n3: ImageView =findViewById(R.id.iv_n3)
        val imv_n4: ImageView =findViewById(R.id.iv_n4)
        val imv_n5: ImageView =findViewById(R.id.iv_n5)
        val imv_n6: ImageView =findViewById(R.id.iv_n6)

        //Nivel 1
        val pn1:TextView = findViewById(R.id.tv_pn1)
        pn1.text=(porc_n1/4).toString()+"%"
        if(comida1=="Res"){
            imv_n1.setImageResource(R.drawable.res)
        }else if(comida1=="Puerco"){
            imv_n1.setImageResource(R.drawable.puerco)
        }else if(comida1=="Pollo"){
            imv_n1.setImageResource(R.drawable.pollo)
        }else if(comida1=="Pescado"){
            imv_n1.setImageResource(R.drawable.pescado)
        }

        //Nivel 2 ...



        //Código para que al pulsar el botón pasa a la ventana correspondiente
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }


    }
}
//sdasdada