package com.example.myrefri

import android.content.Intent
import android.widget.ImageView
import android.widget.TextView

class Nivel(var i_nivel:Int, var lista_comida:Array<String>,
            var lista_tv:Array<TextView>, var lista_imv:Array<ImageView>) {

    //Función para configurar el textView
    fun config_tv(){
        for (i in (0 until this.lista_tv.size)){
            this.lista_tv[i].text=this.lista_comida[0]
        }
    }


    //Función para configutar el imageView
    fun config_imv(){
        val imv_1: ImageView =  this.lista_imv[0]
        val imv_2: ImageView =  this.lista_imv[1]
        val imv_3: ImageView =  this.lista_imv[2]

        if(this.i_nivel==1){
            when(this.lista_comida[0]){
                "Res"    ->  imv_1.setImageResource(R.drawable.res)  ;"Puerco" ->  imv_1.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_1.setImageResource(R.drawable.pollo);"Pescado" -> imv_1.setImageResource(R.drawable.pescado)
            }

            when(this.lista_comida[1]){
                "Res"    ->  imv_2.setImageResource(R.drawable.res)  ;"Puerco"  ->  imv_2.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_2.setImageResource(R.drawable.pollo);"Pescado" ->  imv_2.setImageResource(R.drawable.pescado)
            }
            when(this.lista_comida[2]){
                "Res"    ->  imv_3.setImageResource(R.drawable.res)  ;"Puerco" ->  imv_3.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_3.setImageResource(R.drawable.pollo);"Pescado" -> imv_3.setImageResource(R.drawable.pescado)
            }
        }
        if(this.i_nivel==2){
            when(this.lista_comida[0]){
                "Paletas"    ->  imv_1.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_1.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_1.setImageResource(R.drawable.chocolate)
            }
            when(this.lista_comida[1]){
                "Paletas"    ->  imv_2.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_2.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_2.setImageResource(R.drawable.chocolate)
            }
            when(this.lista_comida[2]){
                "Paletas"    ->  imv_3.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_3.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_3.setImageResource(R.drawable.chocolate)
            }
        }
        if(this.i_nivel==3){
            when(this.lista_comida[0]){
                "Quesos"    ->  imv_1.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_1.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_1.setImageResource(R.drawable.salchicha)
            }
            when(this.lista_comida[1]){
                "Quesos"    ->  imv_2.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_2.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_2.setImageResource(R.drawable.salchicha)
            }
            when(this.lista_comida[2]){
                "Quesos"    ->  imv_3.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_3.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_3.setImageResource(R.drawable.salchicha)
            }
        }
        if(this.i_nivel==4){
            when(this.lista_comida[0]){
                "Yogurt"   ->  imv_1.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_1.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_1.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_1.setImageResource(R.drawable.huevos)
            }
            when(this.lista_comida[1]){
                "Yogurt"   ->  imv_2.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_2.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_2.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_2.setImageResource(R.drawable.huevos)
            }
            when(this.lista_comida[2]){
                "Yogurt"   ->  imv_3.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_3.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_3.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_3.setImageResource(R.drawable.huevos)
            }

        }
        if(this.i_nivel==5){
            when(this.lista_comida[0]){
                "Comida Sobrante" -> imv_1.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_1.setImageResource(R.drawable.agua)
            }
            when(this.lista_comida[1]){
                "Comida Sobrante" -> imv_2.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_2.setImageResource(R.drawable.agua)
            }
            when(this.lista_comida[2]){
                "Comida Sobrante" -> imv_3.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_3.setImageResource(R.drawable.agua)
            }

        }
        if(this.i_nivel==6){
            when(this.lista_comida[0]){
                "Jitomate" ->  imv_1.setImageResource(R.drawable.jitomate)
                "Cebolla"  ->  imv_1.setImageResource(R.drawable.cebolla)
                "Chiles"   ->  imv_1.setImageResource(R.drawable.chiles)
                "Limon"    ->  imv_1.setImageResource(R.drawable.limon)
                "Verduras" ->  imv_1.setImageResource(R.drawable.verduras)
                "Otros"    ->  imv_1.setImageResource(R.drawable.verduras)
            }
            when(this.lista_comida[1]){
                "Jitomate" ->  imv_2.setImageResource(R.drawable.jitomate)
                "Cebolla"  ->  imv_2.setImageResource(R.drawable.cebolla)
                "Chiles"   ->  imv_2.setImageResource(R.drawable.chiles)
                "Limon"    ->  imv_2.setImageResource(R.drawable.limon)
                "Verduras" ->  imv_2.setImageResource(R.drawable.verduras)
                "Otros"    ->  imv_2.setImageResource(R.drawable.verduras)
            }
            when(this.lista_comida[2]){
                "Jitomate" ->  imv_3.setImageResource(R.drawable.jitomate)
                "Cebolla"  ->  imv_3.setImageResource(R.drawable.cebolla)
                "Chiles"   ->  imv_3.setImageResource(R.drawable.chiles)
                "Limon"    ->  imv_3.setImageResource(R.drawable.limon)
                "Verduras" ->  imv_3.setImageResource(R.drawable.verduras)
                "Otros"    ->  imv_3.setImageResource(R.drawable.verduras)
            }
        }
    }


}