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
import java.util.*


class Status : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"

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
        refri4.text = comida4

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
        //Función para configurar al textView
        fun config_tv(s_nivel:String,tv_resource:Int){
            val comida_selec :String = intent.getStringExtra(s_nivel).toString()
            val tv: TextView  =  findViewById(tv_resource)
            tv.text=comida_selec
        }

        //Función para configurar el imageView
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
            if(i_nivel==2){
                when(comida_selec){
                    "Paletas" ->     imv.setImageResource(R.drawable.paleta)
                    "Helado" ->  imv.setImageResource(R.drawable.helado)
                    "Chocolate"  ->  imv.setImageResource(R.drawable.chocolate)
                }
            }
            if(i_nivel==3){
                when(comida_selec){
                    "Quesos" ->     imv.setImageResource(R.drawable.queso)
                    "Jamon" ->  imv.setImageResource(R.drawable.jamon)
                    "Salchicha"  ->  imv.setImageResource(R.drawable.salchicha)
                }
            }
            if(i_nivel==4){
                when(comida_selec){
                    "Yogurt" ->     imv.setImageResource(R.drawable.yogurt)
                    "Leche" ->  imv.setImageResource(R.drawable.leche)
                    "Gelatina"  ->  imv.setImageResource(R.drawable.gelatina)
                    "Huevo" -> imv.setImageResource(R.drawable.huevos)
                }
            }
            if(i_nivel==5){
                when(comida_selec){
                    "Comida Sobrante" -> imv.setImageResource(R.drawable.comidasobrante)
                    "Agua" ->  imv.setImageResource(R.drawable.agua)
                }
            }
            if(i_nivel==6){
                when(comida_selec){
                    "Jitomate" ->     imv.setImageResource(R.drawable.jitomate)
                    "Cebolla" ->  imv.setImageResource(R.drawable.cebolla)
                    "Chiles"  ->  imv.setImageResource(R.drawable.chiles)
                    "Limon" -> imv.setImageResource(R.drawable.limon)
                    "Verduras" -> imv.setImageResource(R.drawable.verduras)
                    "Otros" -> imv.setImageResource(R.drawable.verduras)
                }
            }
        }



        config_tv( "Nivel1",R.id.tv_cn1)
        config_imv("Nivel1",1,R.id.iv_n1)

        config_tv( "Nivel2",R.id.tv_cn2)
        config_imv("Nivel2",2,R.id.iv_n2)

        config_tv( "Nivel3",R.id.tv_cn3)
        config_imv("Nivel3",3,R.id.iv_n3)

        config_tv( "Nivel4",R.id.tv_cn4)
        config_imv("Nivel4",4,R.id.iv_n4)

        config_tv( "Nivel5",R.id.tv_cn5)
        config_imv("Nivel5",5,R.id.iv_n5)

        config_tv( "Nivel6",R.id.tv_cn6)
        config_imv("Nivel6",6,R.id.iv_n6)


        //Código para que al pulsar el botón pasa a la ventana correspondiente
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }


    }
}
