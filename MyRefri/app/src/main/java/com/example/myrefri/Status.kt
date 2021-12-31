package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.*
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

        //---------------------------------------------------------------------
        //Matriz con los nombres para cada nivel y "subnivel", serán la clave/llave para recuperar información de la actividad Main
        var levels_keyname_mat :Array<Array<String>> = arrayOf(
            arrayOf("Nivel1_1","Nivel1_2","Nivel1_3"),
            arrayOf("Nivel2_1","Nivel2_2","Nivel2_3"),
            arrayOf("Nivel3_1","Nivel3_2","Nivel3_3"),
            arrayOf("Nivel4_1","Nivel4_2","Nivel4_3"),
            arrayOf("Nivel5_1","Nivel5_2","Nivel5_3"),
            arrayOf("Nivel6_1","Nivel6_2","Nivel6_3"),
        )

        //Matriz para guardar la información que viene de la actividad Main
        var selected_products_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }


        //Se usa la clave para recuperar la información de la actividad Main y se guarda en la matriz selected_products_mat
        var product_key:String

        for (i in (0 until selected_products_mat.size)){
            for(j in (0 until selected_products_mat[i].size)){
                product_key = levels_keyname_mat[i][j]
                selected_products_mat[i][j] = intent.getStringExtra(product_key).toString()
            }
        }

        //Matrices que contienen la información de los textView e imageView para establecer el nombre del producto y su imagen
        var txtView_mat:Array<Array<TextView>> = arrayOf(
            arrayOf(findViewById(R.id.tv_producto_n1_1),findViewById(R.id.tv_producto_n1_2),findViewById(R.id.tv_producto_n1_3)),
            arrayOf(findViewById(R.id.tv_producto_n2_1),findViewById(R.id.tv_producto_n2_2),findViewById(R.id.tv_producto_n2_3)),
            arrayOf(findViewById(R.id.tv_producto_n3_1),findViewById(R.id.tv_producto_n3_2),findViewById(R.id.tv_producto_n3_3)),
            arrayOf(findViewById(R.id.tv_producto_n4_1),findViewById(R.id.tv_producto_n4_2),findViewById(R.id.tv_producto_n4_3)),
            arrayOf(findViewById(R.id.tv_producto_n5_1),findViewById(R.id.tv_producto_n5_2),findViewById(R.id.tv_producto_n5_3)),
            arrayOf(findViewById(R.id.tv_producto_n6_1),findViewById(R.id.tv_producto_n6_2),findViewById(R.id.tv_producto_n6_3))
        )

        var imgView_mat:Array<Array<ImageView>> = arrayOf(
            arrayOf(findViewById(R.id.iv_n1_1),findViewById(R.id.iv_n1_2),findViewById(R.id.iv_n1_3)),
            arrayOf(findViewById(R.id.iv_n2_1),findViewById(R.id.iv_n2_2),findViewById(R.id.iv_n2_3)),
            arrayOf(findViewById(R.id.iv_n3_1),findViewById(R.id.iv_n3_2),findViewById(R.id.iv_n3_3)),
            arrayOf(findViewById(R.id.iv_n4_1),findViewById(R.id.iv_n4_2),findViewById(R.id.iv_n4_3)),
            arrayOf(findViewById(R.id.iv_n5_1),findViewById(R.id.iv_n5_2),findViewById(R.id.iv_n5_3)),
            arrayOf(findViewById(R.id.iv_n6_1),findViewById(R.id.iv_n6_2),findViewById(R.id.iv_n6_3))
        )


        /*Se crea un objeto de la clase SaveData, se le asigna la información actual y dependiendo de si había o no datos previos
        reasigna nuevamente la matriz selected_products_mat, para posteriormente crear los niveles con esa información */
        var saveData: SaveDataClass = SaveDataClass(selected_products_mat,this)
        selected_products_mat   = saveData.setSaveData()


        //Se crean 6 objetos de la clase Nivel, para configurar cada nivel
        //El número de cada nivel en número entero, productos del nivel correspondiente, los 3 textView correspondientes al nivel, los 3 imageView correspondientes al nivel
        val level1 :LevelClass = LevelClass(1, selected_products_mat[0], txtView_mat[0], imgView_mat[0])
        val level2 :LevelClass = LevelClass(2, selected_products_mat[1], txtView_mat[1], imgView_mat[1])
        val level3 :LevelClass = LevelClass(3, selected_products_mat[2], txtView_mat[2], imgView_mat[2])
        val level4 :LevelClass = LevelClass(4, selected_products_mat[3], txtView_mat[3], imgView_mat[3])
        val level5 :LevelClass = LevelClass(5, selected_products_mat[4], txtView_mat[4], imgView_mat[4])
        val level6 :LevelClass = LevelClass(6, selected_products_mat[5], txtView_mat[5], imgView_mat[5])


        //Se usan las funciones config_tv y config_imv para mostrar el nombre e imagen de cada alimento en la interfaz Status
        level1.setTextViews(); level1.setImagesViews()
        level2.setTextViews(); level2.setImagesViews()
        level3.setTextViews(); level3.setImagesViews()
        level4.setTextViews(); level4.setImagesViews()
        level5.setTextViews(); level5.setImagesViews()
        level6.setTextViews(); level6.setImagesViews()


        //Código para pasar a la actividad ShoppingList y mandar la información de los productos actuales
        val button_shopping_list_Activity = findViewById<Button>(R.id.btn_hacer_lista)
        button_shopping_list_Activity.setOnClickListener{
            val intent_ShoppingListActivity = Intent(this,ShoppingList::class.java)
            var product_key:String

            for (i in (0 until levels_keyname_mat.size)){
                for(j in (0 until levels_keyname_mat[i].size)){
                    product_key = levels_keyname_mat[i][j]
                    intent_ShoppingListActivity.putExtra(product_key,selected_products_mat[i][j])
                }
            }
            startActivity(intent_ShoppingListActivity)
        }

        //Botón para modificar la lista de productos, ir a la actividad Main
        val button_main_Activity = findViewById<Button>(R.id.btn_config_productos)
        button_main_Activity.setOnClickListener{
            val intent_MainActivity = Intent(this,MainActivity::class.java)
            startActivity(intent_MainActivity)
        }

        //Botón para resetear la lista de productos
        val button_reset_products = findViewById<Button>(R.id.btn_reset_productos)
        button_reset_products.setOnClickListener{
            saveData.deleteAllData()
        }
    }

    //---------------------------------------------
    //Código de backend
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
