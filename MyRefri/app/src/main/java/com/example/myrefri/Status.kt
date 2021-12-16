package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.*
import java.util.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import org.w3c.dom.Text

class Status : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        //Base de datos
        database = Firebase.database.reference
        readData("Res")

        //Arreglo con los nombres de los niveles establecidos en la actividad Main
        var niveles_ar :Array<String> = arrayOf("Nivel1","Nivel2","Nivel3","Nivel4","Nivel5","Nivel6")

        //Matriz para posteriormente almacenar la información de la actividad Main
        var productos_mat = arrayOf(
            arrayOf("producto_n1_1","producto_n1_2","producto_n1_3") ,
            arrayOf("producto_n2_1","producto_n2_2","producto_n2_3") ,
            arrayOf("producto_n3_1","producto_n3_2","producto_n3_3") ,
            arrayOf("producto_n4_1","producto_n4_2","producto_n4_3") ,
            arrayOf("producto_n5_1","producto_n5_2","producto_n5_3") ,
            arrayOf("producto_n6_1","producto_n6_2","producto_n6_3") ,
        )

        //Ciclo para obtener el nombre producto de la actividad Main y asignarlo a el producto correspondiente en la matriz productos_main
        for (i in (0 until productos_mat.size)){
            for(j in (0 until productos_mat[i].size)){
                productos_mat[i][j]=intent.getStringExtra(niveles_ar[i]).toString()
            }
        }

        //Arreglos que contienen los id para establecer el nombre del producto y su imagen
        var txtview_ar_n1:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n1_1),findViewById(R.id.tv_producto_n1_2),findViewById(R.id.tv_producto_n1_3))
        var txtview_ar_n2:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n2_1),findViewById(R.id.tv_producto_n2_2),findViewById(R.id.tv_producto_n2_3))
        var txtview_ar_n3:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n3_1),findViewById(R.id.tv_producto_n3_2),findViewById(R.id.tv_producto_n3_3))
        var txtview_ar_n4:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n4_1),findViewById(R.id.tv_producto_n4_2),findViewById(R.id.tv_producto_n4_3))
        var txtview_ar_n5:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n5_1),findViewById(R.id.tv_producto_n5_2),findViewById(R.id.tv_producto_n5_3))
        var txtview_ar_n6:Array<TextView>  = arrayOf(findViewById(R.id.tv_producto_n6_1),findViewById(R.id.tv_producto_n6_2),findViewById(R.id.tv_producto_n6_3))

        var imview_ar_n1:Array<ImageView> = arrayOf(findViewById(R.id.iv_n1_1),findViewById(R.id.iv_n1_2),findViewById(R.id.iv_n1_3))
        var imview_ar_n2:Array<ImageView> = arrayOf(findViewById(R.id.iv_n2_1),findViewById(R.id.iv_n2_2),findViewById(R.id.iv_n2_3))
        var imview_ar_n3:Array<ImageView> = arrayOf(findViewById(R.id.iv_n3_1),findViewById(R.id.iv_n3_2),findViewById(R.id.iv_n3_3))
        var imview_ar_n4:Array<ImageView> = arrayOf(findViewById(R.id.iv_n4_1),findViewById(R.id.iv_n4_2),findViewById(R.id.iv_n4_3))
        var imview_ar_n5:Array<ImageView> = arrayOf(findViewById(R.id.iv_n5_1),findViewById(R.id.iv_n5_2),findViewById(R.id.iv_n5_3))
        var imview_ar_n6:Array<ImageView> = arrayOf(findViewById(R.id.iv_n6_1),findViewById(R.id.iv_n6_2),findViewById(R.id.iv_n6_3))


        //Se crea cada nivel, usando la clase Nivel
        //Nivel en número entero, productos del nivel correspondiente en la matriz de productos, 3 textView correspondientes al nivel, 3 imageView correspondientes al nivel
        val Nivel1 :Nivel = Nivel(1, productos_mat[0], txtview_ar_n1, imview_ar_n1)
        val Nivel2 :Nivel = Nivel(2, productos_mat[1], txtview_ar_n2, imview_ar_n2)
        val Nivel3 :Nivel = Nivel(3, productos_mat[2], txtview_ar_n3, imview_ar_n3)
        val Nivel4 :Nivel = Nivel(4, productos_mat[3], txtview_ar_n4, imview_ar_n4)
        val Nivel5 :Nivel = Nivel(5, productos_mat[4], txtview_ar_n5, imview_ar_n5)
        val Nivel6 :Nivel = Nivel(6, productos_mat[5], txtview_ar_n6, imview_ar_n6)

        //Se usan las funciones config_tv y config_imv para mostrar el nombre e imagen de cada alimento en la interfaz Status
        Nivel1.config_tv();Nivel1.config_imv()
        Nivel2.config_tv();Nivel2.config_imv()
        Nivel3.config_tv();Nivel3.config_imv()
        Nivel4.config_tv();Nivel4.config_imv()
        Nivel5.config_tv();Nivel5.config_imv()
        Nivel6.config_tv();Nivel6.config_imv()

        //Código para pasar a la actividad ShoppingList
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }
    }
    
    //---------------------------------------------
    //Código de backend
    private  fun readData(element: String) {
        database = FirebaseDatabase.getInstance().getReference("Elements")
        database.child(element).get().addOnSuccessListener {
            if (it.exists()) {
                val cantidad = it.value
                val firstLevel = findViewById<TextView>(R.id.tv_kg_n1_1)
                firstLevel.text = "${cantidad.toString()} kg"
                Log.d("TAG", cantidad.toString())
            }
        }
    }
}
