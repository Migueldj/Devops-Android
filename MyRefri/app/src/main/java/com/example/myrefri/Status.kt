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



class Status : AppCompatActivity() {

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var database: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        //Base de datos
        database = Firebase.database.reference
        readData("Res")

        //La variable comida_n, guarda la comida seleccionada de la actividad anterior
        val comida_n1:String =intent.getStringExtra("Nivel1").toString()
        val comida_n2:String =intent.getStringExtra("Nivel2").toString()
        val comida_n3:String =intent.getStringExtra("Nivel3").toString()
        val comida_n4:String =intent.getStringExtra("Nivel4").toString()
        val comida_n5:String =intent.getStringExtra("Nivel5").toString()
        val comida_n6:String =intent.getStringExtra("Nivel6").toString()

        //Se crea cada nivel, usando la clase Nivel

        val Nivel1 :Nivel = Nivel(1,comida_n1,findViewById(R.id.tv_comida_n1_1),findViewById(R.id.iv_n1_1))
        val Nivel2 :Nivel = Nivel(2,comida_n2,findViewById(R.id.tv_comida_n2_1),findViewById(R.id.iv_n2_1))
        val Nivel3 :Nivel = Nivel(3,comida_n3,findViewById(R.id.tv_comida_n3_1),findViewById(R.id.iv_n3_1))
        val Nivel4 :Nivel = Nivel(4,comida_n4,findViewById(R.id.tv_comida_n4_1),findViewById(R.id.iv_n4_1))
        val Nivel5 :Nivel = Nivel(5,comida_n5,findViewById(R.id.tv_comida_n5_1),findViewById(R.id.iv_n5_1))
        val Nivel6 :Nivel = Nivel(6,comida_n6,findViewById(R.id.tv_comida_n6_1),findViewById(R.id.iv_n6_1))

        //Se usan las funciones config_tv para los textView y config config_imv para los ImageView
        //Para mostrar la comida seleccionada en la siguiente interfaz

        Nivel1.config_tv();Nivel1.config_imv()
        Nivel2.config_tv();Nivel2.config_imv()
        Nivel3.config_tv();Nivel3.config_imv()
        Nivel4.config_tv();Nivel4.config_imv()
        Nivel5.config_tv();Nivel5.config_imv()
        Nivel6.config_tv();Nivel6.config_imv()


        //Código para que al pulsar el botón pasa a la ventana correspondiente
        val button = findViewById<Button>(R.id.btn_hacer_lista)
        button.setOnClickListener{
            val intent2 = Intent(this,ShoppingList::class.java)
            startActivity(intent2)
        }


    }

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
