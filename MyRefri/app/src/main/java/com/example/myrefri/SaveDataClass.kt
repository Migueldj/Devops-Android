package com.example.myrefri
import android.app.AlertDialog
import android.content.Context
import android.preference.PreferenceManager
class SaveDataClass(var selected_products_mat :Array<Array<String?>>, var context: Context) {

    //Variable para manejar el Preference Manager
    var prefs=PreferenceManager.getDefaultSharedPreferences(this.context)

    //Matriz que guardará los productos seleccionados
    var saved_products_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }

    //Matriz con las claves para cada producto
    var products_keys_mat :Array<Array<String?>> = arrayOf(
        arrayOf("key_n1_1","key_n1_2","key_n1_3") ,
        arrayOf("key_n2_1","key_n2_2","key_n2_3") ,
        arrayOf("key_n3_1","key_n3_2","key_n3_3") ,
        arrayOf("key_n4_1","key_n4_2","key_n4_3") ,
        arrayOf("key_n5_1","key_n5_2","key_n5_3") ,
        arrayOf("key_n6_1","key_n6_2","key_n6_3") ,
    )

    /*Función para obtener la información DEL PRODUCTO SELECCIONADO con base en los indices i,j que se le den a la función,
      y usando su clave y si ya se guardó algún valor previamente*/
    fun getPreviousData(i:Int,j:Int):String?{
        var previous_data_mat :Array<Array<String?>> = arrayOf(
            arrayOf(prefs.getString(products_keys_mat[0][0],"No hay valores aún"),prefs.getString(products_keys_mat[0][1],"No hay valores aún"),prefs.getString(products_keys_mat[0][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(products_keys_mat[1][0],"No hay valores aún"),prefs.getString(products_keys_mat[1][1],"No hay valores aún"),prefs.getString(products_keys_mat[1][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(products_keys_mat[2][0],"No hay valores aún"),prefs.getString(products_keys_mat[2][1],"No hay valores aún"),prefs.getString(products_keys_mat[2][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(products_keys_mat[3][0],"No hay valores aún"),prefs.getString(products_keys_mat[3][1],"No hay valores aún"),prefs.getString(products_keys_mat[3][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(products_keys_mat[4][0],"No hay valores aún"),prefs.getString(products_keys_mat[4][1],"No hay valores aún"),prefs.getString(products_keys_mat[4][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(products_keys_mat[5][0],"No hay valores aún"),prefs.getString(products_keys_mat[5][1],"No hay valores aún"),prefs.getString(products_keys_mat[5][2],"No hay valores aún")) ,
        )
        return previous_data_mat[i][j]
    }

    //Función para guardar la información de un producto con base en su clave y el producto que se seleccionó
    fun saveData(selected_product:String?,i:Int,j:Int){
        val editor = prefs.edit()
        editor.putString(products_keys_mat[i][j],selected_product)
        editor.apply()
    }

    //Función para configurar el guardado de datos
    //Devuelve la matriz con los datos guardados, esos datos son los que se verán al ejecutar la actividad Status
    fun setSaveData(): Array<Array<String?>> {
        var selected_product:String?
        var current_data: String?

        //Los productos seleccionados que se mencionan (selected_products_mat), son los que se eligen en la actividad Main
        //Se selecciona un producto de la matriz selected_products_mat
            //Si el producto es null, el valor actual del producto será "No hay valores aún", que se encuentre en el índice dado para la matriz previous_data_mat de la función getPreviousData
            //Si el producto tiene algún valor: 1.- Usa la función saveData, para guardar el valor del producto usando una clave
            //                                  2.- El valor actual del producto será el que se encuentre en el índice dado para la matriz previous_data_mat de la función getPreviousData

        for (i in (0 until this.selected_products_mat.size)){
            for(j in (0 until this.selected_products_mat[i].size)){

                selected_product=selected_products_mat[i][j]

                if(selected_product=="null") {
                    current_data=getPreviousData(i,j)
                } else{
                    saveData(selected_product,i,j)
                    current_data=getPreviousData(i,j)
                }
                saved_products_mat[i][j]=current_data
            }
        }
        //Devuelve la matriz con los datos actuales ya guardados
        return saved_products_mat
    }


    //Función para eliminar todos los datos
    fun deleteAllData(){
        for (i in (0 until this.selected_products_mat.size)){
            for(j in (0 until this.selected_products_mat[i].size)){
                var editor=prefs.edit()
                editor.remove(products_keys_mat[i][j])
                editor.apply()
            }
        }
        showAlert("Se han borrado los valores\nPor favor reinicie la aplicación")
    }

    //Función para enviar mensajes de alerta
    fun showAlert(message:String?){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}