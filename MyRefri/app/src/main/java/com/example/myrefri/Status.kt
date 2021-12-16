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

        //Vectores con la información
        var niveles :Array<String> = arrayOf("Nivel1","Nivel2","Nivel3","Nivel4","Nivel5","Nivel6")

        var comidas = arrayOf(
            arrayOf("comida_n1_1","comida_n1_2","comida_n1_3") ,
            arrayOf("comida_n2_1","comida_n2_2","comida_n2_3") ,
            arrayOf("comida_n3_1","comida_n3_2","comida_n3_3") ,
            arrayOf("comida_n4_1","comida_n4_2","comida_n4_3") ,
            arrayOf("comida_n5_1","comida_n5_2","comida_n5_3") ,
            arrayOf("comida_n6_1","comida_n6_2","comida_n6_3") ,
        )

        var tview_n1:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n1_1),findViewById(R.id.tv_comida_n1_2),findViewById(R.id.tv_comida_n1_3))
        var tview_n2:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n2_1),findViewById(R.id.tv_comida_n2_2),findViewById(R.id.tv_comida_n2_3))
        var tview_n3:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n3_1),findViewById(R.id.tv_comida_n3_2),findViewById(R.id.tv_comida_n3_3))
        var tview_n4:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n4_1),findViewById(R.id.tv_comida_n4_2),findViewById(R.id.tv_comida_n4_3))
        var tview_n5:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n5_1),findViewById(R.id.tv_comida_n5_2),findViewById(R.id.tv_comida_n5_3))
        var tview_n6:Array<TextView>   = arrayOf(findViewById(R.id.tv_comida_n6_1),findViewById(R.id.tv_comida_n6_2),findViewById(R.id.tv_comida_n6_3))

        var imview_n1:Array<ImageView> = arrayOf(findViewById(R.id.iv_n1_1),findViewById(R.id.iv_n1_2),findViewById(R.id.iv_n1_3))
        var imview_n2:Array<ImageView> = arrayOf(findViewById(R.id.iv_n2_1),findViewById(R.id.iv_n2_2),findViewById(R.id.iv_n2_3))
        var imview_n3:Array<ImageView> = arrayOf(findViewById(R.id.iv_n3_1),findViewById(R.id.iv_n3_2),findViewById(R.id.iv_n3_3))
        var imview_n4:Array<ImageView> = arrayOf(findViewById(R.id.iv_n4_1),findViewById(R.id.iv_n4_2),findViewById(R.id.iv_n4_3))
        var imview_n5:Array<ImageView> = arrayOf(findViewById(R.id.iv_n5_1),findViewById(R.id.iv_n5_2),findViewById(R.id.iv_n5_3))
        var imview_n6:Array<ImageView> = arrayOf(findViewById(R.id.iv_n6_1),findViewById(R.id.iv_n6_2),findViewById(R.id.iv_n6_3))


        for (i in (0 until comidas.size)){
            for(j in (0 until comidas[i].size)){
                comidas[i][j]=intent.getStringExtra(niveles[i]).toString()
            }
        }



        //Se crea cada nivel, usando la clase Nivel
        val Nivel1 :Nivel = Nivel(1, comidas[0], tview_n1, imview_n1)

        val Nivel2 :Nivel = Nivel(2, comidas[1], tview_n2, imview_n2)

        val Nivel3 :Nivel = Nivel(3, comidas[2], tview_n3, imview_n3)

        val Nivel4 :Nivel = Nivel(4, comidas[3], tview_n4, imview_n4)

        val Nivel5 :Nivel = Nivel(5, comidas[4], tview_n5, imview_n5)

        val Nivel6 :Nivel = Nivel(6, comidas[5], tview_n6, imview_n6)


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
