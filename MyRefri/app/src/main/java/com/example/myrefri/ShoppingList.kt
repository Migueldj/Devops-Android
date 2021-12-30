package com.example.myrefri

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import android.content.Intent

class ShoppingList : AppCompatActivity() {

    private val STORAGE_CODE:Int=100
    var pdf_list:GeneratePDF=GeneratePDF()
    var productos_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping_list)

        //---------------------------------------------------------------------
        var niveles_nombre_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )

        var nivel_nombre:String
        for (i in (0 until productos_mat.size)){
            for(j in (0 until productos_mat[i].size)){
                nivel_nombre=niveles_nombre_mat[i][j]
                productos_mat[i][j]=intent.getStringExtra(nivel_nombre).toString()
            }
        }

        val btn_generar_lista = findViewById<Button>(R.id.btn_generar_lista)
        btn_generar_lista.setOnClickListener{
            if(Build.VERSION.SDK_INT>Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)==PackageManager.PERMISSION_DENIED) {
                    val permissions= arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    requestPermissions(permissions,STORAGE_CODE)
                }else {
                    pdf_list.savePDF(this,productos_mat)
                }
            }else{
                pdf_list.savePDF(this,productos_mat)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            STORAGE_CODE ->{
                if(grantResults.size>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        pdf_list.savePDF(this,productos_mat)
                }else{
                    Toast.makeText(this,"Permisson denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}