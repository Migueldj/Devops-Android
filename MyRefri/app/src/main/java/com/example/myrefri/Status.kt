package com.example.myrefri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Status : AppCompatActivity() {
    var p_carne: Int = 100
    var p_paleta: Int = 100
    var p_leche: Int = 100
    var p_salchicha: Int = 100
    var p_queso: Int = 100

    var sp_carne: String =""
    var sp_paleta: String = ""
    var sp_leche: String = ""
    var sp_salchicha: String = ""
    var sp_queso: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_status)

        //Llamado de funciones



    }
}