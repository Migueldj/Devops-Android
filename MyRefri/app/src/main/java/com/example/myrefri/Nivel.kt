package com.example.myrefri

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView

class Nivel(var i_nivel:Int,var comida:String,var tv_FindById:TextView,var imv_FindById:ImageView) {

    //Función para configurar el textView
    fun config_tv(){
        this.tv_FindById.text=this.comida
    }

    //Función para configutar el imageView
    fun config_imv(){
        val imv: ImageView =  this.imv_FindById
        if(this.i_nivel==1){
            when(this.comida){
                "Res" ->     imv.setImageResource(R.drawable.res)
                "Puerco" ->  imv.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv.setImageResource(R.drawable.pollo)
                "Pescado" -> imv.setImageResource(R.drawable.pescado)
            }
        }
        if(this.i_nivel==2){
            when(this.comida){
                "Paletas" ->     imv.setImageResource(R.drawable.paleta)
                "Helado" ->  imv.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv.setImageResource(R.drawable.chocolate)
            }
        }
        if(this.i_nivel==3){
            when(this.comida){
                "Quesos" ->     imv.setImageResource(R.drawable.queso)
                "Jamon" ->  imv.setImageResource(R.drawable.jamon)
                "Salchicha"  ->  imv.setImageResource(R.drawable.salchicha)
            }
        }
        if(this.i_nivel==4){
            when(this.comida){
                "Yogurt" ->     imv.setImageResource(R.drawable.yogurt)
                "Leche" ->  imv.setImageResource(R.drawable.leche)
                "Gelatina"  ->  imv.setImageResource(R.drawable.gelatina)
                "Huevo" -> imv.setImageResource(R.drawable.huevos)
            }
        }
        if(this.i_nivel==5){
            when(this.comida){
                "Comida Sobrante" -> imv.setImageResource(R.drawable.comidasobrante)
                "Agua" ->  imv.setImageResource(R.drawable.agua)
            }
        }
        if(this.i_nivel==6){
            when(this.comida){
                "Jitomate" ->     imv.setImageResource(R.drawable.jitomate)
                "Cebolla" ->  imv.setImageResource(R.drawable.cebolla)
                "Chiles"  ->  imv.setImageResource(R.drawable.chiles)
                "Limon" -> imv.setImageResource(R.drawable.limon)
                "Verduras" -> imv.setImageResource(R.drawable.verduras)
                "Otros" -> imv.setImageResource(R.drawable.verduras)
            }
        }
    }




}