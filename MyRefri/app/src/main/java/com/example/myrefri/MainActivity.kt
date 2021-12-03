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
class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private val sharedPrefFile = "kotlinsharedpreference"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        val analytics = FirebaseAnalytics.getInstance(this)
        val bundle = Bundle()
        bundle.putString("message","Firebase al 100")
        analytics.logEvent("InitScreen", bundle)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val Nivel1 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel2 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel3 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel4 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel5 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        val Nivel6 = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)

        Nivel1.addAll(Arrays.asList("Res","Puerco","Pollo","Pescado"))
        Nivel2.addAll(Arrays.asList("Paletas","Helado","Chocolate"))
        Nivel3.addAll(Arrays.asList("Quesos","Jamon","Salchicha"))
        Nivel4.addAll(Arrays.asList("Yogurt","Leche","Gelatina","Huevo"))
        Nivel5.addAll(Arrays.asList("Comida Sobrante","Condimentos","Agua"))
        Nivel6.addAll(Arrays.asList("Jitomate","Cebolla","Chiles","Limon","Verduras","Otros"))

        mBinding.desplegable1.adapter = Nivel1
        mBinding.desplegable2.adapter = Nivel2
        mBinding.desplegable3.adapter = Nivel3
        mBinding.desplegable4.adapter = Nivel4
        mBinding.desplegable5.adapter = Nivel5
        mBinding.desplegable6.adapter = Nivel6


        val nivel1 = findViewById<Spinner>(R.id.desplegable_1)
        val button = findViewById<Button>(R.id.btn_aceptar_ingreso)
        button.setOnClickListener{
            var texto1:String = nivel1.selectedItem.toString()
            val intent = Intent(this,Status::class.java)
            intent.putExtra("Nivel1",texto1)
            startActivity(intent)
        }
        
    }
}