package com.example.myrefri
import android.app.AlertDialog
import android.content.Context
import android.preference.PreferenceManager
class SaveData(var productos_mat :Array<Array<String?>>,var context: Context) {

    //Variable para manejar el Preference Manager
    var prefs=PreferenceManager.getDefaultSharedPreferences(this.context)

    //Matriz que guardará los productos seleccionados
    var saved_productos_mat :Array<Array<String?>> = Array(6) {Array(3) {""} }

    //Matriz con las claves para cada proeucto
    var keys_mat :Array<Array<String?>> = arrayOf(
        arrayOf("key_n1_1","key_n1_2","key_n1_3") ,
        arrayOf("key_n2_1","key_n2_2","key_n2_3") ,
        arrayOf("key_n3_1","key_n3_2","key_n3_3") ,
        arrayOf("key_n4_1","key_n4_2","key_n4_3") ,
        arrayOf("key_n5_1","key_n5_2","key_n5_3") ,
        arrayOf("key_n6_1","key_n6_2","key_n6_3") ,
    )

    //Función para configurar el guardado de datos
    //Devuelve la matriz con los datos guardados
    fun configSaveData(): Array<Array<String?>> {
        var producto:String?
        var data: String?

        //Los productos seleccionados que se mencionan, son los que se eligen en la actividad Main
        //Si se seleccionó un producto para cierto nivel:
            //Si el producto es null, guarda en la matriz saved_productos_mat "No hay valores aún"
            //Si el producto tiene algún valor, "No hay valores aún" el producto seleccionado
        for (i in (0 until this.productos_mat.size)){
            for(j in (0 until this.productos_mat[i].size)){
                producto=productos_mat[i][j]
                if(producto=="null") {
                    data=getData(i,j)
                } else{
                    saveData(producto,i,j)
                    data=getData(i,j)
                }
                saved_productos_mat[i][j]=data
            }
        }
        //showAlert("Se han guardado los valores")
        return saved_productos_mat
    }

    //Función para obtener la información de los productos seleccionados con base en su clave y si ya se guardó algún valor previamente
    fun getData(i:Int,j:Int):String?{
        var myPrefs_mat :Array<Array<String?>> = arrayOf(
            arrayOf(prefs.getString(keys_mat[0][0],"No hay valores aún"),prefs.getString(keys_mat[0][1],"No hay valores aún"),prefs.getString(keys_mat[0][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(keys_mat[1][0],"No hay valores aún"),prefs.getString(keys_mat[1][1],"No hay valores aún"),prefs.getString(keys_mat[1][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(keys_mat[2][0],"No hay valores aún"),prefs.getString(keys_mat[2][1],"No hay valores aún"),prefs.getString(keys_mat[2][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(keys_mat[3][0],"No hay valores aún"),prefs.getString(keys_mat[3][1],"No hay valores aún"),prefs.getString(keys_mat[3][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(keys_mat[4][0],"No hay valores aún"),prefs.getString(keys_mat[4][1],"No hay valores aún"),prefs.getString(keys_mat[4][2],"No hay valores aún")) ,
            arrayOf(prefs.getString(keys_mat[5][0],"No hay valores aún"),prefs.getString(keys_mat[5][1],"No hay valores aún"),prefs.getString(keys_mat[5][2],"No hay valores aún")) ,
        )
        return myPrefs_mat[i][j]
    }

    //Función para guardar la información de un producto con base en su clave y el producto que se seleccionó
    fun saveData(producto:String?,i:Int,j:Int){
        val editor = prefs.edit()
        editor.putString(keys_mat[i][j],producto)
        editor.apply()
    }

    //Función para eliminar todos los datos
    fun deleteAllData(){
        for (i in (0 until this.productos_mat.size)){
            for(j in (0 until this.productos_mat[i].size)){
                var editor=prefs.edit()
                editor.remove(keys_mat[i][j])
                editor.apply()
            }
        }
        showAlert("Hemos borrado los valores\nPor favor reinicie la aplicación")
    }

    fun showAlert(message:String?){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }
}