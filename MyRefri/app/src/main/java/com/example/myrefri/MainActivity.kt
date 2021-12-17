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

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        var productos_n1 = Arrays.asList("Res","Puerco","Pollo","Pescado")
        var productos_n2 = Arrays.asList("Paletas","Helado","Chocolate")
        var productos_n3 = Arrays.asList("Quesos","Jamon","Salchicha")
        var productos_n4 = Arrays.asList("Yogurt","Leche","Gelatina","Huevo")
        var productos_n5 = Arrays.asList("Comida Sobrante","Agua")
        var productos_n6 = Arrays.asList("Jitomate","Cebolla","Chiles","Limon","Verduras","Otros")

        val Nivel1_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel2_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel3_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel4_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel5_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel6_1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)


        Nivel1_1.addAll(productos_n1)
        Nivel2_1.addAll(productos_n2)
        Nivel3_1.addAll(productos_n3)
        Nivel4_1.addAll(productos_n4)
        Nivel5_1.addAll(productos_n5)
        Nivel6_1.addAll(productos_n6)

        mBinding.desplegable1.adapter = Nivel1_1
        mBinding.desplegable2.adapter = Nivel2_1
        mBinding.desplegable3.adapter = Nivel3_1
        mBinding.desplegable4.adapter = Nivel4_1
        mBinding.desplegable5.adapter = Nivel5_1
        mBinding.desplegable6.adapter = Nivel6_1


        //Variables para guardar la opción elegida y mandarla a la siguiente ventana
        val nivel1 = findViewById<Spinner>(R.id.desplegable_1)
        val nivel2 = findViewById<Spinner>(R.id.desplegable_2)
        val nivel3 = findViewById<Spinner>(R.id.desplegable_3)
        val nivel4 = findViewById<Spinner>(R.id.desplegable_4)
        val nivel5 = findViewById<Spinner>(R.id.desplegable_5)
        val nivel6 = findViewById<Spinner>(R.id.desplegable_6)


        var niveles_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),

        )

        var texto :Array<String> = arrayOf(nivel1.selectedItem.toString(),nivel2.selectedItem.toString(),nivel3.selectedItem.toString(),
                                           nivel4.selectedItem.toString(),nivel5.selectedItem.toString(),nivel6.selectedItem.toString())


        val button = findViewById<Button>(R.id.btn_aceptar_ingreso)
        button.setOnClickListener{
            val intent = Intent(this,Status::class.java)
            for (i in (0 until niveles_mat.size)){
                for(j in (0 until niveles_mat[i].size-2)){

                    intent.putExtra(niveles_mat[i][j],texto[i])

                }
            }
            startActivity(intent)
        }
        
    }
}

