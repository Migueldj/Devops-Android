package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

        //---------------------------------------------------------------------
        //Matriz con los mismos nombres de los niveles establecidos en la actividad Main
        var niveles_nombre_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )

        //Matriz para posteriormente almacenar la información de la actividad Main
        //Usaremos esta información para crear cada Objeto Nivel
        var productos_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }


        //Obten la información del Spinner que viene ligada al nombre_nivel de la actividad Main
        //Asigna esa información a cada elemento de la matriz productos_mat

        //Variable para obtener la información del Spinner ligado al nombre de la variable
        var nivel_nombre:String

        for (i in (0 until productos_mat.size)){
            for(j in (0 until productos_mat[i].size)){
                nivel_nombre=niveles_nombre_mat[i][j]
                productos_mat[i][j]=intent.getStringExtra(nivel_nombre).toString()
            }
        }

        //----------------------------------------------
        //Matrices que contienen la información de los textView e imageView para establecer el nombre del producto y su imagen
        var txtView_mat:Array<Array<TextView>> = arrayOf(
            arrayOf(findViewById(R.id.tv_producto_n1_1),findViewById(R.id.tv_producto_n1_2),findViewById(R.id.tv_producto_n1_3)),
            arrayOf(findViewById(R.id.tv_producto_n2_1),findViewById(R.id.tv_producto_n2_2),findViewById(R.id.tv_producto_n2_3)),
            arrayOf(findViewById(R.id.tv_producto_n3_1),findViewById(R.id.tv_producto_n3_2),findViewById(R.id.tv_producto_n3_3)),
            arrayOf(findViewById(R.id.tv_producto_n4_1),findViewById(R.id.tv_producto_n4_2),findViewById(R.id.tv_producto_n4_3)),
            arrayOf(findViewById(R.id.tv_producto_n5_1),findViewById(R.id.tv_producto_n5_2),findViewById(R.id.tv_producto_n5_3)),
            arrayOf(findViewById(R.id.tv_producto_n6_1),findViewById(R.id.tv_producto_n6_2),findViewById(R.id.tv_producto_n6_3))
        )

        var imgView_mat:Array<Array<ImageView>> = arrayOf(
            arrayOf(findViewById(R.id.iv_n1_1),findViewById(R.id.iv_n1_2),findViewById(R.id.iv_n1_3)),
            arrayOf(findViewById(R.id.iv_n2_1),findViewById(R.id.iv_n2_2),findViewById(R.id.iv_n2_3)),
            arrayOf(findViewById(R.id.iv_n3_1),findViewById(R.id.iv_n3_2),findViewById(R.id.iv_n3_3)),
            arrayOf(findViewById(R.id.iv_n4_1),findViewById(R.id.iv_n4_2),findViewById(R.id.iv_n4_3)),
            arrayOf(findViewById(R.id.iv_n5_1),findViewById(R.id.iv_n5_2),findViewById(R.id.iv_n5_3)),
            arrayOf(findViewById(R.id.iv_n6_1),findViewById(R.id.iv_n6_2),findViewById(R.id.iv_n6_3))
        )

        //-----------------------------------
        //Código para guardar los datos, usa los datos actuales para crear un objeto de la clase SaveData
        //Dependiendo de si había datos guardados o no, asignale valores nuevamente a la matriz productos_mat
        var save_data: SaveData= SaveData(productos_mat,this)
        productos_mat=save_data.configSaveData()

        //-----------------------------------
        //Se crea cada nivel, usando la clase Nivel
        //Nivel en número entero, productos del nivel correspondiente, los 3 textView correspondientes al nivel, los 3 imageView correspondientes al nivel
        val Nivel1 :Nivel = Nivel(1, productos_mat[0], txtView_mat[0], imgView_mat[0])
        val Nivel2 :Nivel = Nivel(2, productos_mat[1], txtView_mat[1], imgView_mat[1])
        val Nivel3 :Nivel = Nivel(3, productos_mat[2], txtView_mat[2], imgView_mat[2])
        val Nivel4 :Nivel = Nivel(4, productos_mat[3], txtView_mat[3], imgView_mat[3])
        val Nivel5 :Nivel = Nivel(5, productos_mat[4], txtView_mat[4], imgView_mat[4])
        val Nivel6 :Nivel = Nivel(6, productos_mat[5], txtView_mat[5], imgView_mat[5])

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

        //Botón para configurar la lista de productos
        var falButton_1 = findViewById<FloatingActionButton>(R.id.flBtn_1)
        falButton_1.setOnClickListener{
            val intent3 = Intent(this,MainActivity::class.java)
            startActivity(intent3)
        }

        //Botón para reseteal la lista de productos
        var falButton_2 = findViewById<FloatingActionButton>(R.id.flBtn_2)
        falButton_2.setOnClickListener{
            save_data.deleteAllData()
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
