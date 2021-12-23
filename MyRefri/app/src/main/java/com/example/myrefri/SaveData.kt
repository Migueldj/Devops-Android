package com.example.myrefri
import android.app.AlertDialog
import android.content.Context
import android.preference.PreferenceManager
class SaveData(var dato:String?,var context: Context) {

    val key = "MyKey"
    val prefs=PreferenceManager.getDefaultSharedPreferences(this.context)
/*
    fun config_tv(){
        for (i in (0 until this.productos_ar.size)){
            var produto:String = this.productos_ar[i]
            if(produto=="null") {
                // No hagas nada
            } else{
               //Guarda esa información y después muestrala
            }
        }
    }
*/

    fun configSD(): String? {
        val producto:String?=this.dato
        val data: String?

        if(producto=="null") {
            data=getData()
        } else{
            saveData(producto)
            data=getData()
        }
        return data

    }


    fun getData():String?{
        val myPref = prefs.getString(key,"No hay valores aún")
        showAlert(myPref)
        return myPref
    }

    fun saveData(producto:String?){
        val editor = prefs.edit()
        editor.putString(key,producto)
        editor.apply()
        showAlert("Hemos guardado un valor")
    }

    fun deleteData(){
        val editor=prefs.edit()
        editor.remove(key)
        editor.apply()
        showAlert("Hemos borrado un valor")
    }

    fun showAlert(message:String?){
        val builder = AlertDialog.Builder(this.context)
        builder.setTitle("My preferences")
        builder.setMessage(message)
        val dialog = builder.create()
        dialog.show()
    }



}