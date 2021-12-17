package com.example.myrefri

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.analytics.FirebaseAnalytics
import androidx.appcompat.app.AppCompatActivity
import com.example.myrefri.databinding.ActivityMainBinding
import java.util.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase


class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Firebase.database
        val myRef = database.getReference("Comprobando")

        myRef.setValue("Sí está conectada la base de datos")

        setContentView(R.layout.activity_main)
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Firebase al 100")
        analytics.logEvent("InitScreen", bundle)

//----------------------------------------------------------
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        //Arreglos con el tipo de producto por nivel
        var productos_n1 = Arrays.asList("Res","Puerco","Pollo","Pescado")
        var productos_n2 = Arrays.asList("Paletas","Helado","Chocolate")
        var productos_n3 = Arrays.asList("Quesos","Jamon","Salchicha")
        var productos_n4 = Arrays.asList("Yogurt","Leche","Gelatina","Huevo")
        var productos_n5 = Arrays.asList("Comida Sobrante","Agua")
        var productos_n6 = Arrays.asList("Jitomate","Cebolla","Chiles","Limon","Verduras","Otros")

        // Código para configurar los Spinner
        val Nivel1_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel1_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel1_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        val Nivel2_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel2_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel2_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        val Nivel3_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel3_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel3_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        val Nivel4_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel4_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel4_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        val Nivel5_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel5_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel5_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        val Nivel6_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel6_2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel6_3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        //Se le asigna a los 3 productos de cada nivel, el mismo tipo de productos, correspondientes a cada nivel
        Nivel1_1.addAll(productos_n1);Nivel1_2.addAll(productos_n1);Nivel1_3.addAll(productos_n1)
        Nivel2_1.addAll(productos_n2);Nivel2_2.addAll(productos_n2);Nivel2_3.addAll(productos_n2)
        Nivel3_1.addAll(productos_n3);Nivel3_2.addAll(productos_n3);Nivel3_3.addAll(productos_n3)
        Nivel4_1.addAll(productos_n4);Nivel4_2.addAll(productos_n4);Nivel4_3.addAll(productos_n4)
        Nivel5_1.addAll(productos_n5);Nivel5_2.addAll(productos_n5);Nivel5_3.addAll(productos_n5)
        Nivel6_1.addAll(productos_n6);Nivel6_2.addAll(productos_n6);Nivel6_3.addAll(productos_n6)

        //Se configuran los desplegables de cada nivel
        //(Por alguna razón "desplegable no acepta guión bajo, entonces desplegable11 "equivale a 1_1")
        mBinding.desplegable11.adapter = Nivel1_1
        mBinding.desplegable12.adapter = Nivel1_2
        mBinding.desplegable13.adapter = Nivel1_3

        mBinding.desplegable21.adapter = Nivel2_1
        mBinding.desplegable22.adapter = Nivel2_2
        mBinding.desplegable23.adapter = Nivel2_3

        mBinding.desplegable31.adapter = Nivel3_1
        mBinding.desplegable32.adapter = Nivel3_2
        mBinding.desplegable33.adapter = Nivel3_3

        mBinding.desplegable41.adapter = Nivel4_1
        mBinding.desplegable42.adapter = Nivel4_2
        mBinding.desplegable43.adapter = Nivel4_3

        mBinding.desplegable51.adapter = Nivel5_1
        mBinding.desplegable52.adapter = Nivel5_2
        mBinding.desplegable53.adapter = Nivel5_3

        mBinding.desplegable61.adapter = Nivel6_1
        mBinding.desplegable62.adapter = Nivel6_2
        mBinding.desplegable63.adapter = Nivel6_3


        //Matriz que contienen la información del Spinner de los 3 productos de cada nivel, con ellos obtendremos la selección del usuario
        var niveles_spinner_mat:Array<Array<Spinner>> = arrayOf(
            arrayOf(findViewById<Spinner>(R.id.desplegable_1_1),findViewById<Spinner>(R.id.desplegable_1_2),findViewById<Spinner>(R.id.desplegable_1_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_2_1),findViewById<Spinner>(R.id.desplegable_2_2),findViewById<Spinner>(R.id.desplegable_2_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_3_1),findViewById<Spinner>(R.id.desplegable_3_2),findViewById<Spinner>(R.id.desplegable_3_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_4_1),findViewById<Spinner>(R.id.desplegable_4_2),findViewById<Spinner>(R.id.desplegable_4_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_5_1),findViewById<Spinner>(R.id.desplegable_5_2),findViewById<Spinner>(R.id.desplegable_5_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_6_1),findViewById<Spinner>(R.id.desplegable_6_2),findViewById<Spinner>(R.id.desplegable_6_3)),
        )

        //Matriz con los nombres para cada nivel y "subnivel", servirán para enviar la información a la actividad Status
        var niveles_nombre_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )


        val button = findViewById<Button>(R.id.btn_aceptar_ingreso)
        button.setOnClickListener{
            val intent = Intent(this,Status::class.java)

            //Variable para llevar la información del spinner correspondiente a la actividad Status
            //es una variable contenida en niveles_nombre_mat
            var nivel_nombre:String

            //Variable para obtener la información del spinner correspondiente
            //es una variable contenida en niveles_spinner_mat
            var nivel_spinner:String


            for (i in (0 until niveles_nombre_mat.size)){
                for(j in (0 until niveles_nombre_mat[i].size)){

                    nivel_spinner=niveles_spinner_mat[i][j].selectedItem.toString()
                    nivel_nombre=niveles_nombre_mat[i][j]

                    //Liga la información de nivel_nombre con nivel_spinner y mándalo a la siguiente actividad
                    intent.putExtra(nivel_nombre,nivel_spinner)
                }
            }
            startActivity(intent)
        }
        
    }
}

