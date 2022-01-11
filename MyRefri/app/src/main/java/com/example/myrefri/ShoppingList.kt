package com.example.myrefri

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import java.util.*

class ShoppingList : AppCompatActivity() {
    //Variable para usar en la creación del pdf
    private val STORAGE_CODE:Int=100

    //Se crea un objeto de la clase GeneratePDF a nivel global
    var pdfList:GeneratePDFClass=GeneratePDFClass()

    //Si inicializa una matriz de 3x6, posteriormente se llenará con los productos seleccionados que aparecen en la actividad Status
    var selected_products_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }

    //Se inicializa una matriz con todos los productos posibles por nivel
    var all_products_mat :Array<Array<String?>> = arrayOf(
        arrayOf("Res","Puerco","Pollo","Pescado","Otros","Vacío"),
        arrayOf("Paletas","Helado","Hielo","Otros","Vacío"),
        arrayOf("Queso","Jamón","Salchicha","Otros","Vacío"),
        arrayOf("Yogurt","Leche","Gelatina","Huevo","Otros","Vacío"),
        arrayOf("Agua","Jugo","Comida Sobrante","Otros","Vacío"),
        arrayOf("Jitomate","Cebolla","Chiles","Limón","Verduras","Otros","Vacío"),
    )

    //Array  para guardar la información que viene de la actividad Main, de los productos ingresados manualmente
    var written_products_arr :Array<String?> = Array(6,{""})

    //Matriz para la cantidad en kg que hay de cada alimento
    var weight_mat :Array<Array<Float>> =  Array(6) {Array(4) {0.0f} }

    //Matriz que contendrá los id de los checkbox
    lateinit var checkbox_id_mat:Array<Array<View?>>

    //Matriz que contendrá los id de los checkbox para los productos ingresados manualmente
    lateinit var written_products_checkbox_arr:Array<View?>

    private val sharedPrefFile = "kotlinsharedpreference"
    private lateinit var database: DatabaseReference

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)
        database = Firebase.database.reference

        //Código que lee los datos de la base de datos
        readData()

        //---------------------------------------------------------------------
        //El siguiente código funciona de la misma manera que en la actividad Status
        var levels_keyname_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )

        var product_key:String
        for (i in (0 until selected_products_mat.size)){
            for(j in (0 until selected_products_mat[i].size)){
                product_key = levels_keyname_mat[i][j]
                selected_products_mat[i][j] = intent.getStringExtra(product_key).toString()
            }
        }

        /*Este código funciona de igual manera que el anterior, pero trabaja con la información que el usuario agrega manualmente*/
        var levels_keyname_editText_arr :Array<String> = arrayOf(
            "Nivel1_4","Nivel2_4","Nivel3_4","Nivel4_4","Nivel5_4","Nivel6_4"
        )

        //Se usa la clave para recuperar la información de la actividad Main y se guarda en written_products_arr
        var written_product_key:String

        for(i in (0 until written_products_arr.size)){
            written_product_key = levels_keyname_editText_arr[i]
            written_products_arr[i] = intent.getStringExtra(written_product_key).toString()
        }

    //--------------------------------------------------------
        //Matriz con la información para localizar los checkbox de la actividad Shopping List
        checkbox_id_mat= arrayOf(
        arrayOf(findViewById<CheckBox>(R.id.cBox_n1_1),findViewById<CheckBox>(R.id.cBox_n1_2),findViewById<CheckBox>(R.id.cBox_n1_3),findViewById<CheckBox>(R.id.cBox_n1_4),findViewById<CheckBox>(R.id.cBox_n1_5)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n2_1),findViewById<CheckBox>(R.id.cBox_n2_2),findViewById<CheckBox>(R.id.cBox_n2_3),findViewById<CheckBox>(R.id.cBox_n2_4)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n3_1),findViewById<CheckBox>(R.id.cBox_n3_2),findViewById<CheckBox>(R.id.cBox_n3_3),findViewById<CheckBox>(R.id.cBox_n3_4)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n4_1),findViewById<CheckBox>(R.id.cBox_n4_2),findViewById<CheckBox>(R.id.cBox_n4_3),findViewById<CheckBox>(R.id.cBox_n4_4),findViewById<CheckBox>(R.id.cBox_n4_5)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n5_1),findViewById<CheckBox>(R.id.cBox_n5_2),findViewById<CheckBox>(R.id.cBox_n5_3)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n6_1),findViewById<CheckBox>(R.id.cBox_n6_2),findViewById<CheckBox>(R.id.cBox_n6_3),findViewById<CheckBox>(R.id.cBox_n6_4),findViewById<CheckBox>(R.id.cBox_n6_5),findViewById<CheckBox>(R.id.cBox_n6_6)),
        )

        //Array con los id de los checkbox que se usarán para la los productos que el usuario ingresó manualmente
        written_products_checkbox_arr= arrayOf(checkbox_id_mat[0][4],checkbox_id_mat[1][3],checkbox_id_mat[2][3],checkbox_id_mat[3][4],checkbox_id_mat[4][2],checkbox_id_mat[5][5])

        //---------------------------------------------------------------------
        /*La función configPDF gestiona los permisos para la escritura en la memoria y poder generar el PDF,
        también hace uso de la funcion savePDFWithCBox*/
        fun configPDF(){
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED) {
                    val permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions,STORAGE_CODE)
                }else {
                    pdfList.savePDFWithCBox(this,all_products_mat,written_products_arr,checkbox_id_mat)
                }
            }else{
                pdfList.savePDFWithCBox(this,all_products_mat,written_products_arr,checkbox_id_mat)
            }
        }


        //Código del botón, que usa la función configPDF

        val button_set_list = findViewById<Button>(R.id.btn_generar_lista)

        button_set_list.setOnClickListener{
            configPDF()
        }

    }

    //Código para generar el PDF
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_CODE ->{
                if(grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    pdfList.savePDFWithCBox(this,all_products_mat,written_products_arr,checkbox_id_mat)
                }else{
                    Toast.makeText(this,"Permisson denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private  fun readData() {
        database = FirebaseDatabase.getInstance().getReference("Elements")
        val valueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                /*val value = dataSnapshot.getValue<Long>()
                val firstLevel = findViewById<TextView>(R.id.tv_kg_n1_1)
                firstLevel.text = "${value.toString()} kg"*/
                var values_names_mat :Array<Array<String>> = arrayOf(
                    arrayOf("Elemento1","Elemento2","Elemento3","Elemento4"),
                    arrayOf("Elemento5","Elemento6","Elemento7","Elemento8"),
                    arrayOf("Elemento9","Elemento10","Elemento11","Elemento12"),
                    arrayOf("Elemento13","Elemento14","Elemento15","Elemento16"),
                    arrayOf("Elemento17","Elemento18","Elemento19","Elemento20"),
                    arrayOf("Elemento21","Elemento22","Elemento23","Elemento24"),
                )
                for (i in (0 until 6)){
                    for(j in (0 until 4)){

                        weight_mat[i][j]=dataSnapshot.child(values_names_mat[i][j]).getValue<Float>()!!.toFloat()
                    }
                }
                /*Se utiliza la función preSelectedCheckBox para preseleccionar los checkbox de la actividad Shopping list, dependiendo de la cantidad en kg que haya de cada alimento, así como de los 3 productos
                 por nivel que se hayan seleccionado desde la actividad Main, mismos que aparecen en la actividad Status*/
                pdfList.preSelectedCheckBox(all_products_mat,checkbox_id_mat,selected_products_mat,weight_mat)

                /*Se utiliza la función setWrittenProductsCheckBox para dar nombre a los checkbox con base en los productos que ingresó el usuario manualmente
                * también, funciona de manera similar a la función  preSelectedCheckBox, preseleccionando los checkbox, con base en los "kg" que tiene cada producto*/
                pdfList.setWrittenProductsCheckBox(written_products_checkbox_arr ,written_products_arr  ,weight_mat)

            }
            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "loadValue:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(valueListener)
    }
}