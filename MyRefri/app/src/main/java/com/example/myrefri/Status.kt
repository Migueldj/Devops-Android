package com.example.myrefri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
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

        //---------------------------------------------------------------------------------
        /*Este código funciona de igual manera que el anterior, pero trabaja con la información que el usuario agrega manualmente*/
        var levels_keyname_editText_arr :Array<String> = arrayOf(
            "Nivel1_4","Nivel2_4","Nivel3_4","Nivel4_4","Nivel5_4","Nivel6_4"
        )
        //Array  para guardar la información que viene de la actividad Main, de los productos ingresados manualmente
        var written_products_arr :Array<String?> = Array(6,{""})
        //Se usa la clave para recuperar la información de la actividad Main y se guarda en written_products_arr
        var written_product_key:String

        for(i in (0 until written_products_arr.size)){
            written_product_key = levels_keyname_editText_arr[i]
            written_products_arr[i] = intent.getStringExtra(written_product_key).toString()
        }
        //---------------------------------------------------------------------------------


        //Matrices que contienen la información de los textView e imageView para establecer el nombre del producto y su imagen
        var txtView_mat:Array<Array<TextView>> = arrayOf(
            arrayOf(findViewById(R.id.tv_producto_n1_1),findViewById(R.id.tv_producto_n1_2),findViewById(R.id.tv_producto_n1_3)),
            arrayOf(findViewById(R.id.tv_producto_n2_1),findViewById(R.id.tv_producto_n2_2),findViewById(R.id.tv_producto_n2_3)),
            arrayOf(findViewById(R.id.tv_producto_n3_1),findViewById(R.id.tv_producto_n3_2),findViewById(R.id.tv_producto_n3_3)),
            arrayOf(findViewById(R.id.tv_producto_n4_1),findViewById(R.id.tv_producto_n4_2),findViewById(R.id.tv_producto_n4_3)),
            arrayOf(findViewById(R.id.tv_producto_n5_1),findViewById(R.id.tv_producto_n5_2),findViewById(R.id.tv_producto_n5_3)),
            arrayOf(findViewById(R.id.tv_producto_n6_1),findViewById(R.id.tv_producto_n6_2),findViewById(R.id.tv_producto_n6_3))
        )

        var txtView_written_prodducts_arr:Array<TextView> = arrayOf(
            findViewById(R.id.tv_producto_n1_4),findViewById(R.id.tv_producto_n2_4),findViewById(R.id.tv_producto_n3_4),
            findViewById(R.id.tv_producto_n4_4),findViewById(R.id.tv_producto_n5_4),findViewById(R.id.tv_producto_n6_4)
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
        var saveData: SaveDataClass = SaveDataClass(selected_products_mat,written_products_arr,this)
        selected_products_mat   = saveData.setSaveData()
        written_products_arr    = saveData.setSaveDataWritten()

        //Se crean 6 objetos de la clase Nivel, para configurar cada nivel
        //El número de cada nivel en número entero, productos del nivel correspondiente, los 3 textView correspondientes al nivel, los 3 imageView correspondientes al nivel
        val level1 :LevelClass = LevelClass(1, selected_products_mat[0], txtView_mat[0], imgView_mat[0],written_products_arr[0],txtView_written_prodducts_arr[0])
        val level2 :LevelClass = LevelClass(2, selected_products_mat[1], txtView_mat[1], imgView_mat[1],written_products_arr[1],txtView_written_prodducts_arr[1])
        val level3 :LevelClass = LevelClass(3, selected_products_mat[2], txtView_mat[2], imgView_mat[2],written_products_arr[2],txtView_written_prodducts_arr[2])
        val level4 :LevelClass = LevelClass(4, selected_products_mat[3], txtView_mat[3], imgView_mat[3],written_products_arr[3],txtView_written_prodducts_arr[3])
        val level5 :LevelClass = LevelClass(5, selected_products_mat[4], txtView_mat[4], imgView_mat[4],written_products_arr[4],txtView_written_prodducts_arr[4])
        val level6 :LevelClass = LevelClass(6, selected_products_mat[5], txtView_mat[5], imgView_mat[5],written_products_arr[5],txtView_written_prodducts_arr[5])


        //Se usan las funciones config_tv y config_imv para mostrar el nombre e imagen de cada alimento en la interfaz Status
        level1.setTextViews(); level1.setImagesViews()
        level2.setTextViews(); level2.setImagesViews()
        level3.setTextViews(); level3.setImagesViews()
        level4.setTextViews(); level4.setImagesViews()
        level5.setTextViews(); level5.setImagesViews()
        level6.setTextViews(); level6.setImagesViews()

        val button_shopping_list_Activity = findViewById<Button>(R.id.btn_hacer_lista)

        //Código para bloquear el botón si aún no hay productos para generar la lista
        button_shopping_list_Activity.isEnabled = !(selected_products_mat[0][0]=="null"||selected_products_mat[0][0]=="No hay valores aún")

        //Código para pasar a la actividad ShoppingList y mandar la información de los productos actuales
        button_shopping_list_Activity.setOnClickListener{
            val intent_ShoppingListActivity = Intent(this,ShoppingList::class.java)
            var product_key:String

            for (i in (0 until levels_keyname_mat.size)){
                for(j in (0 until levels_keyname_mat[i].size)){
                    product_key = levels_keyname_mat[i][j]
                    intent_ShoppingListActivity.putExtra(product_key,selected_products_mat[i][j])
                }
            }
            //Código para enviar  ShoppingList, la información de los productos que el usuario ingresó manualmente
            var level_keyname_editTex:String
            var level_info_editText:String?
            for (i in (0 until levels_keyname_editText_arr.size)){
                level_info_editText   = written_products_arr[i]
                level_keyname_editTex = levels_keyname_editText_arr[i]
                intent_ShoppingListActivity.putExtra(level_keyname_editTex,level_info_editText)
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
        val valueListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                /*val value = dataSnapshot.getValue<Long>()
                val firstLevel = findViewById<TextView>(R.id.tv_kg_n1_1)
                firstLevel.text = "${value.toString()} kg"*/
                val value1 = dataSnapshot.child("Elemento1").value
                val element1 = findViewById<TextView>(R.id.tv_kg_n1_1)
                element1.text = "${value1.toString()} kg"
                val value2 = dataSnapshot.child("Elemento2").value
                val element2 = findViewById<TextView>(R.id.tv_kg_n1_2)
                element2.text = "${value2.toString()} kg"
                val value3 = dataSnapshot.child("Elemento3").value
                val element3 = findViewById<TextView>(R.id.tv_kg_n1_3)
                element3.text = "${value3.toString()} kg"
                val value4 = dataSnapshot.child("Elemento4").value
                val element4 = findViewById<TextView>(R.id.tv_kg_n1_4)
                element4.text = "${value4.toString()} kg"
                val value5 = dataSnapshot.child("Elemento5").value
                val element5 = findViewById<TextView>(R.id.tv_kg_n2_1)
                element5.text = "${value5.toString()} kg"
                val value6 = dataSnapshot.child("Elemento6").value
                val element6 = findViewById<TextView>(R.id.tv_kg_n2_2)
                element6.text = "${value6.toString()} kg"
                val value7 = dataSnapshot.child("Elemento7").value
                val element7 = findViewById<TextView>(R.id.tv_kg_n2_3)
                element7.text = "${value7.toString()} kg"
                val value8 = dataSnapshot.child("Elemento8").value
                val element8 = findViewById<TextView>(R.id.tv_kg_n2_4)
                element8.text = "${value8.toString()} kg"
                val value9 = dataSnapshot.child("Elemento9").value
                val element9 = findViewById<TextView>(R.id.tv_kg_n3_1)
                element9.text = "${value9.toString()} kg"
                val value10 = dataSnapshot.child("Elemento10").value
                val element10 = findViewById<TextView>(R.id.tv_kg_n3_2)
                element10.text = "${value10.toString()} kg"
                val value11 = dataSnapshot.child("Elemento11").value
                val element11 = findViewById<TextView>(R.id.tv_kg_n3_3)
                element11.text = "${value11.toString()} kg"
                val value12 = dataSnapshot.child("Elemento12").value
                val element12 = findViewById<TextView>(R.id.tv_kg_n3_4)
                element12.text = "${value12.toString()} kg"
                val value13 = dataSnapshot.child("Elemento13").value
                val element13 = findViewById<TextView>(R.id.tv_kg_n4_1)
                element13.text = "${value13.toString()} kg"
                val value14 = dataSnapshot.child("Elemento14").value
                val element14 = findViewById<TextView>(R.id.tv_kg_n4_2)
                element14.text = "${value14.toString()} kg"
                val value15 = dataSnapshot.child("Elemento15").value
                val element15 = findViewById<TextView>(R.id.tv_kg_n4_3)
                element15.text = "${value15.toString()} kg"
                val value16 = dataSnapshot.child("Elemento16").value
                val element16 = findViewById<TextView>(R.id.tv_kg_n4_4)
                element16.text = "${value16.toString()} kg"
                val value17 = dataSnapshot.child("Elemento17").value
                val element17 = findViewById<TextView>(R.id.tv_kg_n5_1)
                element17.text = "${value17.toString()} kg"
                val value18 = dataSnapshot.child("Elemento18").value
                val element18 = findViewById<TextView>(R.id.tv_kg_n5_2)
                element18.text = "${value18.toString()} kg"
                val value19 = dataSnapshot.child("Elemento19").value
                val element19 = findViewById<TextView>(R.id.tv_kg_n5_3)
                element19.text = "${value19.toString()} kg"
                val value20 = dataSnapshot.child("Elemento20").value
                val element20 = findViewById<TextView>(R.id.tv_kg_n5_4)
                element20.text = "${value20.toString()} kg"
                val value21 = dataSnapshot.child("Elemento21").value
                val element21 = findViewById<TextView>(R.id.tv_kg_n6_1)
                element21.text = "${value21.toString()} kg"
                val value22 = dataSnapshot.child("Elemento22").value
                val element22 = findViewById<TextView>(R.id.tv_kg_n6_2)
                element22.text = "${value22.toString()} kg"
                val value23 = dataSnapshot.child("Elemento23").value
                val element23 = findViewById<TextView>(R.id.tv_kg_n6_3)
                element23.text = "${value23.toString()} kg"
                val value24 = dataSnapshot.child("Elemento8").value
                val element24 = findViewById<TextView>(R.id.tv_kg_n6_4)
                element24.text = "${value24.toString()} kg"
                Log.d("DataArray", value1.toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("TAG", "loadValue:onCancelled", databaseError.toException())
            }
        }
        database.addValueEventListener(valueListener)
    }
}
/*    private  fun readData(element: String) {
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
}*/
