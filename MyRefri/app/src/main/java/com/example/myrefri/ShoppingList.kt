package com.example.myrefri

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi

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
        arrayOf("Comida Sobrante","Agua","Jugo","Otros","Vacío"),
        arrayOf("Jitomate","Cebolla","Chiles","Limón","Verduras","Otros","Vacío"),
    )

    //Matriz que contendrá los id de los checkbox
    lateinit var checkbox_id_mat:Array<Array<View?>>

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

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

    //--------------------------------------------------------
     checkbox_id_mat= arrayOf(
        arrayOf(findViewById<CheckBox>(R.id.cBox_n1_1),findViewById<CheckBox>(R.id.cBox_n1_2),findViewById<CheckBox>(R.id.cBox_n1_3),findViewById<CheckBox>(R.id.cBox_n1_4),findViewById<CheckBox>(R.id.cBox_n1_5)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n2_1),findViewById<CheckBox>(R.id.cBox_n2_2),findViewById<CheckBox>(R.id.cBox_n2_3),findViewById<CheckBox>(R.id.cBox_n2_4)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n3_1),findViewById<CheckBox>(R.id.cBox_n3_2),findViewById<CheckBox>(R.id.cBox_n3_3),findViewById<CheckBox>(R.id.cBox_n3_4)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n4_1),findViewById<CheckBox>(R.id.cBox_n4_2),findViewById<CheckBox>(R.id.cBox_n4_3),findViewById<CheckBox>(R.id.cBox_n4_4),findViewById<CheckBox>(R.id.cBox_n4_5)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n5_1),findViewById<CheckBox>(R.id.cBox_n5_2),findViewById<CheckBox>(R.id.cBox_n5_3)),
        arrayOf(findViewById<CheckBox>(R.id.cBox_n6_1),findViewById<CheckBox>(R.id.cBox_n6_2),findViewById<CheckBox>(R.id.cBox_n6_3),findViewById<CheckBox>(R.id.cBox_n6_4),findViewById<CheckBox>(R.id.cBox_n6_5),findViewById<CheckBox>(R.id.cBox_n6_6)),
    )

        //---------------------------------------------------------------------
        /*La función configPDF gestiona los permisos para la escritura en la memoria y poder generar el PDF,
        también hace uso de la funcion savePDFWithCBox*/
        fun configPDF(){

            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED) {
                    val permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions,STORAGE_CODE)
                }else {
                    pdfList.savePDFWithCBox(this,all_products_mat,checkbox_id_mat)
                }
            }else{
                pdfList.savePDFWithCBox(this,all_products_mat,checkbox_id_mat)
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
                    pdfList.savePDFWithCBox(this,all_products_mat,checkbox_id_mat)
                }else{
                    Toast.makeText(this,"Permisson denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}