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
