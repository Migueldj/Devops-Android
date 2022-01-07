package com.example.myrefri
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Environment
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*


class GeneratePDFClass {

    /*Variables para la generación del documento PDF, nombre del archivo y ruta del archivo, para visualizar el archivo creado
    se debe buscar en la carpeta Documents*/
    var mDoc: Document =Document()
    @RequiresApi(Build.VERSION_CODES.N)
    var mFileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
    var mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/"+mFileName+".pdf"  // Environment.DIRECTORY_DOWNLOADS+"/"+mFileName+".pdf"

    /*Función para pre seleccionar los checkbox de la actividad Shopping list, se tiene una matriz de 6x3 con los productos seleccionados por el usuario, se elige un producto de esa matriz,
    * después se busca ese producto en la matriz que contiene todos los alimentos, una vez hecho eso, devuelve el índice correspondiente.
    *
    * La matriz checkbox_id_mat es una matriz dentada, ya que contiene más checkbox de algunos renglones que otros, y también no cuenta con checkbox para la opción de VACÍO, por lo que el índice
    * id_index debe ser menor a la longitud del renglón en cuestión
    *
    * Los elementos de la matriz all_products_mat coinciden en posición con los de checkbox_id_mat, por ejemplo, el elemento[0][0] de la matriz all_products_mat corresónde a RES,
    * y elemento[0][0] de la matriz checkbox_id_mat contiene la información del checkbox ligado al texto RES y de esa manera con los demás, sin embargo, como se menciona en el renglón anterior,
    * en la matriz checkbox_id_mat no existen elementos ligados a VACIO
    *
    * Finalmente la matriz weight_mat es una matriz de 6x3, la cual simula la cantidad en Kg que hay de queda producto, en este caso, si es menor a 3 Kg, se seleccionará el checkbox corresóndiente
    * */

    fun preSelectedCheckBox(all_products_mat: Array<Array<String?>>, checkbox_id_mat: Array<Array<View?>>,
                            selected_products_mat: Array<Array<String?>>, weight_mat :Array<Array<Int>>) {
        var view: View?
        for (i in (0 until 6)) {
            for (j in (0 until 3)) {

                var id_index: Int = all_products_mat[i].indexOf(selected_products_mat[i][j])

                if(id_index<checkbox_id_mat[i].size){
                    if (weight_mat[i][j] < 3) {
                        view = checkbox_id_mat[i][id_index]
                        if (view is CheckBox) {
                            view.isChecked = true
                        }
                    }
                }

            }
        }


    }

    /*Función para configurar los checkbox con base en los productos que el usuario ingresa manualmente, da el nombre a cada checkbox respectivamente y lo preselecciona
     dependiendo el peso simulado de la matriz weight_mat*/
    fun setWrittenProductsCheckBox(written_products_checkbox_arr:Array<View?> ,written_products_arr :Array<String?> ,weight_mat :Array<Array<Int>>){
        for(i in (0 until written_products_checkbox_arr.size)){
            var view:View?=written_products_checkbox_arr[i]
            if(view is CheckBox){
                if(written_products_arr[i]!="No hay valores aún"){
                    view.text=written_products_arr[i]
                }else{
                    view.text=""
                    view.isEnabled=false
                }
            }
            if(weight_mat[i][3]<3){
                view = written_products_checkbox_arr[i]
                if(view is CheckBox){
                    if(view.text!="")
                        view.isChecked=true
                }
            }
        }
    }


    //Función para crear una lista en formato PDF usando los valores true y false, que darían los checkbox de la actividad ShoppingList
    /*La función usa un renglón de la matriz que contiene todos los alimentos, después obtiene la longitud de ese renglón y crea un nuevo array de Strings con esa longitud, selected_products_arr
    *
    * La función usa un renglón  de la matriz que contiene la información de los checkbox, si están seleccionados o no en forma de boolean
    *
    * Si el checkbox está seleccionado, guarda en selected_products_arr, el producto que se encuentra en el renglón de la matriz que contiene todos los alimentos, si no, guarda ""
    *
    * Si el producto i que está en selected_products_arr[i] es "", no hagas nada, si contiene información, agrega un parrafo al pdf con ese producto
    *  */
    fun setPDFListCheckBox(products_per_level: Array<String?>,checkbox_arr: Array<Boolean?>){

        var length:Int = checkbox_arr.size
        var selected_products_arr:Array<String?> = Array(length,{""})

        for (i in (0 until length)){
            if(checkbox_arr[i]==true) {
                selected_products_arr[i]=products_per_level[i]
            } else{
                selected_products_arr[i]=""
            }
        }
        for (i in (0 until length)){
            var selected_product:String? = selected_products_arr[i]
            if(selected_product=="") {

            } else{
                mDoc.add(Paragraph("-"+selected_product))
            }
        }
    }

    //Función que devuelve una matriz de Boolean, dependiendo de si los elementos de una matriz de Checkbox están seleccionados o no
    /*
    * La función usa los view que se encuentran en la matriz checkbox_id_mat, si el view está seleccionado agrega un true en la matriz checkbox_result_mat,
    * si no, agrega un false.
    * */
    fun checkBoxMatrixResults(checkbox_id_mat:Array<Array<View?>>):Array<Array<Boolean?>>{
        var view: View?
        var checkbox_result_mat :Array<Array<Boolean?>> = Array(6) {Array(6) {false} }

        for (i in (0 until checkbox_id_mat.size)){
            for(j in (0 until checkbox_id_mat[i].size)){
                view=checkbox_id_mat[i][j]
                if(view is CheckBox){
                    var checked=view.isChecked
                    if(checked) {checkbox_result_mat[i][j]=true}
                }
            }
        }
        return checkbox_result_mat
    }

    /*Como algunas funciones trabajan con base en los indices de matrices y arreglos ya definidos, y para no alterar dichas funciones
     se creo la función changeLastRowProduct, la cual se encarga de modificar el penúltimo elemento de cada renglón de la modified_matrix = matriz all_products_mat
     cambiará el dato "Otros", por el dato que el usuario haya ingresado manualmente, todos estos datos serán excepto para el dato que está en modified_matrix[4][2], ya que al no considerar "Comida sobrante"
      como una posible compra, el algoritmo no funciona correctamente para este elemento, por lo que se modifica a parte*/

    fun changeLastRowProduct(all_products_mat: Array<Array<String?>>,written_products:Array<String?>):Array<Array<String?>>{
        var index:Int
        var modified_matrix:Array<Array<String?>> = all_products_mat
        for(i in (0 until 6)){
            index=(modified_matrix[i].size)-2
            modified_matrix[i][index]=written_products[i]
        }
        modified_matrix[4][2] = written_products[4]
        return modified_matrix
    }


    /*
    * Esta función hace uso de las 2 anteriores setPDFListCheckBox, y checkBoxMatrixResults y recibe las matrices necesarias y después
    * se las pasa a las 2 funciones anteriores en forma de Array para ir agregando parrafos al PDF nivel por nivel
    * */
    @RequiresApi(Build.VERSION_CODES.N)
    fun savePDFWithCBox(context: Context,all_products_mat: Array<Array<String?>>,written_products:Array<String?>,checkbox_id_mat:Array<Array<View?>>) {

        var checkbox_bool_mat: Array<Array<Boolean?>> = checkBoxMatrixResults(checkbox_id_mat)
        var modified_matrix:Array<Array<String?>>     = changeLastRowProduct(all_products_mat,written_products)

        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()
            mDoc.addAuthor("Juan")
            mDoc.add(Paragraph("==============================Lista de Compras==============================\n"))
            mDoc.add(Paragraph("\n*****Nivel 1*****"))
            setPDFListCheckBox(modified_matrix[0],checkbox_bool_mat[0])
            mDoc.add(Paragraph("\n*****Nivel 2*****"))
            setPDFListCheckBox(modified_matrix[1],checkbox_bool_mat[1])
            mDoc.add(Paragraph("\n*****Nivel 3*****"))
            setPDFListCheckBox(modified_matrix[2],checkbox_bool_mat[2])
            mDoc.add(Paragraph("\n*****Nivel 4*****"))
            setPDFListCheckBox(modified_matrix[3],checkbox_bool_mat[3])
            mDoc.add(Paragraph("\n*****Nivel 5*****"))
            setPDFListCheckBox(modified_matrix[4],checkbox_bool_mat[4])
            mDoc.add(Paragraph("\n*****Nivel 6*****"))
            setPDFListCheckBox(modified_matrix[5],checkbox_bool_mat[5])
            mDoc.close()
            Toast.makeText(context,"$mFileName.pdf\nis save to\n$mFilePath",Toast.LENGTH_SHORT).show()
            Toast.makeText(context,"Para generar otra lista es necesario reiniciar la aplicación",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }
}