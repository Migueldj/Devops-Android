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

        //------------------------------------------------------------------------------------------
        //Matriz con el tipo de producto por nivel
        var products_by_level_mat:Array<Array<MutableList<String>>> = arrayOf(
            arrayOf(Arrays.asList("Res","Puerco","Pollo","Pescado","Vacío")),
            arrayOf(Arrays.asList("Paletas","Helado","Hielo","Vacío")),
            arrayOf(Arrays.asList("Queso","Jamón","Salchicha","Vacío")),
            arrayOf(Arrays.asList("Yogurt","Leche","Gelatina","Huevo","Vacío")),
            arrayOf(Arrays.asList("Comida Sobrante","Agua","Jugo","Vacío")),
            arrayOf(Arrays.asList("Jitomate","Cebolla","Chiles","Limón","Verduras","Vacío")),
        )

        //Matriz de 6x3 (6 niveles y 3 subniveles), para configurar el ArrayAdapter de los spinners
        var levels_aadapter_mat:Array<Array<ArrayAdapter<String>>> = Array(6) {Array(3) {ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)} }


        //Para cada ArrayApater de la matriz levels_aadapter_mat, agrega los productos del nivel correspondiente, de la matriz products_by_level_mat
        for (i in (0 until levels_aadapter_mat.size)){
            for(j in (0 until levels_aadapter_mat[i].size)){
                levels_aadapter_mat[i][j].addAll(products_by_level_mat[i][0])
            }
        }

        //Se configuran los desplegables de cada nivel
        //(Por alguna razón "desplegable no acepta guión bajo, entonces desplegable11 "equivale a 1_1")
        mBinding.desplegable11.adapter = levels_aadapter_mat[0][0]
        mBinding.desplegable12.adapter = levels_aadapter_mat[0][1]
        mBinding.desplegable13.adapter = levels_aadapter_mat[0][2]

        mBinding.desplegable21.adapter = levels_aadapter_mat[1][0]
        mBinding.desplegable22.adapter = levels_aadapter_mat[1][1]
        mBinding.desplegable23.adapter = levels_aadapter_mat[1][2]

        mBinding.desplegable31.adapter = levels_aadapter_mat[2][0]
        mBinding.desplegable32.adapter = levels_aadapter_mat[2][1]
        mBinding.desplegable33.adapter = levels_aadapter_mat[2][2]

        mBinding.desplegable41.adapter = levels_aadapter_mat[3][0]
        mBinding.desplegable42.adapter = levels_aadapter_mat[3][1]
        mBinding.desplegable43.adapter = levels_aadapter_mat[3][2]

        mBinding.desplegable51.adapter = levels_aadapter_mat[4][0]
        mBinding.desplegable52.adapter = levels_aadapter_mat[4][1]
        mBinding.desplegable53.adapter = levels_aadapter_mat[4][2]

        mBinding.desplegable61.adapter = levels_aadapter_mat[5][0]
        mBinding.desplegable62.adapter = levels_aadapter_mat[5][1]
        mBinding.desplegable63.adapter = levels_aadapter_mat[5][2]


        //Matriz que contienen la información del Spinner de los 3 productos de cada nivel, con ellos obtendremos la selección del usuario
        var levels_info_spinner_mat:Array<Array<Spinner>> = arrayOf(
            arrayOf(findViewById<Spinner>(R.id.desplegable_1_1),findViewById<Spinner>(R.id.desplegable_1_2),findViewById<Spinner>(R.id.desplegable_1_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_2_1),findViewById<Spinner>(R.id.desplegable_2_2),findViewById<Spinner>(R.id.desplegable_2_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_3_1),findViewById<Spinner>(R.id.desplegable_3_2),findViewById<Spinner>(R.id.desplegable_3_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_4_1),findViewById<Spinner>(R.id.desplegable_4_2),findViewById<Spinner>(R.id.desplegable_4_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_5_1),findViewById<Spinner>(R.id.desplegable_5_2),findViewById<Spinner>(R.id.desplegable_5_3)),
            arrayOf(findViewById<Spinner>(R.id.desplegable_6_1),findViewById<Spinner>(R.id.desplegable_6_2),findViewById<Spinner>(R.id.desplegable_6_3)),
        )

        //Matriz con los nombres para cada nivel y "subnivel", serán la clave/llave para enviar información a la actividad Status
        var levels_keyname_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )


        //Array con la información de los id donde el usuario puede ingresar un producto manualmente
        var levels_info_editTex_arr :Array<EditText> = arrayOf(
            findViewById<EditText>(R.id.edit_text_n1),
            findViewById<EditText>(R.id.edit_text_n2),
            findViewById<EditText>(R.id.edit_text_n3),
            findViewById<EditText>(R.id.edit_text_n4),
            findViewById<EditText>(R.id.edit_text_n5),
            findViewById<EditText>(R.id.edit_text_n6)
        )

        //Array con los nombres para los productos ingresados manualmente, serán la clave/llave para enviar información a la actividad Status
        var levels_keyname_editText_arr :Array<String> = arrayOf(
            "Nivel1_4","Nivel2_4","Nivel3_4","Nivel4_4","Nivel5_4","Nivel6_4"
        )



        val button = findViewById<Button>(R.id.btn_aceptar_ingreso)
        button.setOnClickListener{
            val intent_StatusActivity = Intent(this,Status::class.java)

            //Es la clave para llevar la información del spinner correspondiente a la actividad Status, obtendrá su valor de levels_keyname_mat
            var level_keyname:String

            //Variable para obtener la información del spinner correspondiente obtendrá su valor delevels_info_spinner_mat
            var level_info_spinner:String

            /*Para cada elemento de levels_info_spinner_mat (producto seleccionado por el usuario) conviertelo en String, envíalo junto con una clave
            a la actividad Status*/
            for (i in (0 until levels_keyname_mat.size)){
                for(j in (0 until levels_keyname_mat[i].size)){
                    level_info_spinner =levels_info_spinner_mat[i][j].selectedItem.toString()
                    level_keyname      =levels_keyname_mat[i][j]

                    intent_StatusActivity.putExtra(level_keyname,level_info_spinner)
                }
            }

            /*Este código funciona de la misma manera que el anterior, envía la información de los productos ingresados manualmente*/
            var level_keyname_editTex:String
            var level_info_editText:String
            for (i in (0 until levels_info_editTex_arr.size)){
                level_info_editText   = levels_info_editTex_arr[i].text.toString()
                level_keyname_editTex = levels_keyname_editText_arr[i]
                intent_StatusActivity.putExtra(level_keyname_editTex,level_info_editText)

            }

            //Inicia la actividad Status
            startActivity(intent_StatusActivity)
        }
    }

}

