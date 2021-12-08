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




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        //Código para que se despliegue en statusV2 lo correspondiente a la ventana en main
        //val comida1:String = intent.getStringExtra("Nivel1").toString()
        // val refri1: TextView = findViewById(R.id.tv_cn1)
        //refri1.text = comida1

        val comida2:String = intent.getStringExtra("Nivel2").toString()
        val refri2: TextView = findViewById(R.id.tv_cn2)
        refri2.text = comida2

        val comida3:String = intent.getStringExtra("Nivel3").toString()
        val refri3: TextView = findViewById(R.id.tv_cn3)
        refri3.text = comida3

        val comida4:String = intent.getStringExtra("Nivel4").toString()
        val refri4: TextView = findViewById(R.id.tv_cn4)
        refri4.text = comida4

        val comida5:String = intent.getStringExtra("Nivel5").toString()
        val refri5: TextView = findViewById(R.id.tv_cn5)
        refri5.text = comida5

        val comida6:String = intent.getStringExtra("Nivel6").toString()
        val refri6: TextView = findViewById(R.id.tv_cn6)
        refri6.text = comida6
        //------------------------------------------------------------------------------------------/
        //Seleccionar imágen dependiendo de la selección del usuario

        // val imv_n1: ImageView =findViewById(R.id.iv_n1)
        val imv_n2: ImageView =findViewById(R.id.iv_n2)
        val imv_n3: ImageView =findViewById(R.id.iv_n3)
        val imv_n4: ImageView =findViewById(R.id.iv_n4)
        val imv_n5: ImageView =findViewById(R.id.iv_n5)
        val imv_n6: ImageView =findViewById(R.id.iv_n6)



        fun config_tv(s_nivel:String,tv_resource:Int){
            val comida_selec :String = intent.getStringExtra(s_nivel).toString()
            val tv: TextView  =  findViewById(tv_resource)
            tv.text=comida_selec

        }

        fun config_imv(s_nivel:String,i_nivel:Int,imv_resource:Int){

            val comida_selec :String = intent.getStringExtra(s_nivel).toString()
            val imv:ImageView =  findViewById(imv_resource)


            if(i_nivel==1){
                when(comida_selec){
                    "Res" ->     imv.setImageResource(R.drawable.res)
                    "Puerco" ->  imv.setImageResource(R.drawable.puerco)
                    "Pollo"  ->  imv.setImageResource(R.drawable.pollo)
                    "Pescado" -> imv.setImageResource(R.drawable.pescado)
                }
            }
        }

        config_tv( "Nivel1",R.id.tv_cn1)
        config_imv("Nivel1",1,R.id.iv_n1)



        //Código para que al pulsar el botón pasa a la ventana correspondiente
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }


    }
}
