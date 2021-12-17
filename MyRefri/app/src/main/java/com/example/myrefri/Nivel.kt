package com.example.myrefri
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView

class Nivel(var nivel_int:Int, var productos_ar:Array<String>, var txtview_ar:Array<TextView>, var imview_ar:Array<ImageView>) {


    //------------------Función para configurar los textView de un nivel

    //Para los 3 textView del nivel elegido, asignale a cada uno, el producto correspondiente
    fun config_tv(){
        for (i in (0 until this.txtview_ar.size)){

            this.txtview_ar[i].text=this.productos_ar[i]
        }
    }


    //--------------------Función para configurar los imageView de cada nivel

    fun config_imv(){
        //Asigna a la variable imv_ los imageView del nivel que recibió el constructor
        val imv_1: ImageView =  this.imview_ar[0]
        val imv_2: ImageView =  this.imview_ar[1]
        val imv_3: ImageView =  this.imview_ar[2]

        //Si el constructor recibió el nivel 1
        if(this.nivel_int==1){
            //Cuando el producto sea "..." establece el imv_1 del nivel 1 como "..."
            when(this.productos_ar[0]){
                "Res"    ->  imv_1.setImageResource(R.drawable.res)  ;"Puerco" ->  imv_1.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_1.setImageResource(R.drawable.pollo);"Pescado" -> imv_1.setImageResource(R.drawable.pescado)
            }
            //Cuando el producto sea "..." establece el imv_2 del nivel 1 como "..."
            when(this.productos_ar[1]){
                "Res"    ->  imv_2.setImageResource(R.drawable.res)  ;"Puerco"  ->  imv_2.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_2.setImageResource(R.drawable.pollo);"Pescado" ->  imv_2.setImageResource(R.drawable.pescado)
            }
            when(this.productos_ar[2]){
                "Res"    ->  imv_3.setImageResource(R.drawable.res)  ;"Puerco" ->  imv_3.setImageResource(R.drawable.puerco)
                "Pollo"  ->  imv_3.setImageResource(R.drawable.pollo);"Pescado" -> imv_3.setImageResource(R.drawable.pescado)
            }
        }
        //Si el constructor recibió el nivel 2
        if(this.nivel_int==2){
            //Cuando el producto sea "..." establece el imv_1 del nivel 2 como "..."
            when(this.productos_ar[0]){
                "Paletas"    ->  imv_1.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_1.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_1.setImageResource(R.drawable.chocolate)
            }
            //Cuando el producto sea "..." establece el imv_2 del nivel 2 como "..."
            when(this.productos_ar[1]){
                "Paletas"    ->  imv_2.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_2.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_2.setImageResource(R.drawable.chocolate)
            }
            when(this.productos_ar[2]){
                "Paletas"    ->  imv_3.setImageResource(R.drawable.paleta)
                "Helado"     ->  imv_3.setImageResource(R.drawable.helado)
                "Chocolate"  ->  imv_3.setImageResource(R.drawable.chocolate)
            }
        }
        if(this.nivel_int==3){
            when(this.productos_ar[0]){
                "Quesos"    ->  imv_1.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_1.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_1.setImageResource(R.drawable.salchicha)
            }
            when(this.productos_ar[1]){
                "Quesos"    ->  imv_2.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_2.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_2.setImageResource(R.drawable.salchicha)
            }
            when(this.productos_ar[2]){
                "Quesos"    ->  imv_3.setImageResource(R.drawable.queso)
                "Jamon"     ->  imv_3.setImageResource(R.drawable.jamon)
                "Salchicha" ->  imv_3.setImageResource(R.drawable.salchicha)
            }
        }
        if(this.nivel_int==4){
            when(this.productos_ar[0]){
                "Yogurt"   ->  imv_1.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_1.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_1.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_1.setImageResource(R.drawable.huevos)
            }
            when(this.productos_ar[1]){
                "Yogurt"   ->  imv_2.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_2.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_2.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_2.setImageResource(R.drawable.huevos)
            }
            when(this.productos_ar[2]){
                "Yogurt"   ->  imv_3.setImageResource(R.drawable.yogurt)
                "Leche"    ->  imv_3.setImageResource(R.drawable.leche)
                "Gelatina" ->  imv_3.setImageResource(R.drawable.gelatina)
                "Huevo"    ->  imv_3.setImageResource(R.drawable.huevos)
            }
        }
        if(this.nivel_int==5){
            when(this.productos_ar[0]){
                "Comida Sobrante" -> imv_1.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_1.setImageResource(R.drawable.agua)
            }
            when(this.productos_ar[1]){
                "Comida Sobrante" -> imv_2.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_2.setImageResource(R.drawable.agua)
            }
            when(this.productos_ar[2]){
                "Comida Sobrante" -> imv_3.setImageResource(R.drawable.comidasobrante)
                "Agua"            -> imv_3.setImageResource(R.drawable.agua)
            }
        }
        if(this.nivel_int==6){
            when(this.productos_ar[0]){
                "Jitomate" ->  imv_1.setImageResource(R.drawable.jitomate)
                "Cebolla"  ->  imv_1.setImageResource(R.drawable.cebolla)
                "Chiles"   ->  imv_1.setImageResource(R.drawable.chiles)
                "Limon"    ->  imv_1.setImageResource(R.drawable.limon)
                "Verduras" ->  imv_1.setImageResource(R.drawable.verduras)
                "Otros"    ->  imv_1.setImageResource(R.drawable.verduras)
            }
            when(this.productos_ar[1]){
                "Jitomate" ->  imv_2.setImageResource(R.drawable.jitomate)
                "Cebolla"  ->  imv_2.setImageResource(R.drawable.cebolla)
                "Chiles"   ->  imv_2.setImageResource(R.drawable.chiles)
                "Limon"    ->  imv_2.setImageResource(R.drawable.limon)
                "Verduras" ->  imv_2.setImageResource(R.drawable.verduras)
                "Otros"    ->  imv_2.setImageResource(R.drawable.verduras)
            }
            when(this.productos_ar[2]){
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