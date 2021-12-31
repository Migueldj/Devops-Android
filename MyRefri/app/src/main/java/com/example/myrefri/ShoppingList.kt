package com.example.myrefri

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi

class ShoppingList : AppCompatActivity() {
    //Variable para usar en la creación del pdf
    private val STORAGE_CODE:Int=100
    //Se crea un objeto de la clase GeneratePDF a nivel global
    var pdfList:GeneratePDFClass=GeneratePDFClass()

    /*------ NOTA: Las siguientes 2 matrices se usan en casos diferentes, de momento para la realización de pruebas
    Con la primera (selected_products_mat) se genera el pdf usando los productos seleccionados que aparecen en Status, con
    la siguiente matriz (all_products_mat) se usa en conjunto con otra matriz (check_box_mat) de tipo Boolean para seleccionar ciertos elementos de todos los posibles
    ---*/

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
    //Se inicializa una matriz con valores al azar de tipo Boolean con fines de pruebas-
    var check_box_mat :Array<Array<Boolean>> = arrayOf(
        arrayOf(true,true,true,true,true,true),
        arrayOf(true,true,true,true,true),
        arrayOf(true,true,true,true,true),
        arrayOf(true,true,true,true,true,true),
        arrayOf(true,true,true,true,true),
        arrayOf(true,true,true,true,true,true,true),
    )


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

        //---------------------------------------------------------------------
        /*La función configPDF gestiona los permisos para la escritura en la memoria y poder generar el PDF,
        también hace uso de las funciones savePDFStatus, savePDFCBox, que son las 2 maneras diferentes de generar el PDF (de momento de prueba),
        savePDFStatus genera un PDF con base en los productos seleccionados que coinciden con los de la actividadStatus, savePDFCBox genera un PDF simulando
        los valores true y false, que devolverían los checkbox de la actividad ShoppingList, para la realización de pruebas se debe dejar alguna de las 2
        comentada */
        fun configPDF(){
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED) {
                    val permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions,STORAGE_CODE)
                }else {
                    pdfList.savePDFWithStatus(this,selected_products_mat)
                    //pdfList.savePDFWithCBox(this,all_products_mat,check_box_mat)
                }
            }else{
                pdfList.savePDFWithStatus(this,selected_products_mat)
                //pdfList.savePDFWithCBox(this,all_products_mat,check_box_mat)
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
                        pdfList.savePDFWithStatus(this,selected_products_mat)
                    //pdfList.savePDFWithCBox(this,all_products_mat,check_box_mat)
                }else{
                    Toast.makeText(this,"Permisson denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}