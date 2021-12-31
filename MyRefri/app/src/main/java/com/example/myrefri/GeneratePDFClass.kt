package com.example.myrefri
import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.os.Environment
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

    //Función para crear una lista en formato PDF usando los productos seleccionados, que coinciden con los de la actividad Status

    fun setPDFListStatus(selected_products_arr: Array<String?>){
        for (i in (0 until selected_products_arr.size)){
            var selected_product:String? = selected_products_arr[i]
            if(selected_product=="null"||selected_product==""||selected_product==null) {

            } else{
                mDoc.add(Paragraph("-"+selected_product))
            }
        }
    }

    //Función para crear una lista en formato PDF simulando los valores true y false, que darían los checkbox de la clase ShoppingList
    fun setPDFListCheckBox(products_per_level: Array<String?>,checkbox_arr: Array<Boolean>){

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

    //Función para guardar el pdf, usando la función setPDFListStatus
    @RequiresApi(Build.VERSION_CODES.N)
     fun savePDFWithStatus(context: Context,selected_products_mat:Array<Array<String?>>) {
        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()
            mDoc.addAuthor("Juan")
            mDoc.add(Paragraph("==============================Lista de Compras==============================\n"))

            mDoc.add(Paragraph("\n*****Nivel 1*****"))
            setPDFListStatus(selected_products_mat[0])

            mDoc.add(Paragraph("\n*****Nivel 2*****"))
            setPDFListStatus(selected_products_mat[1])

            mDoc.add(Paragraph("\n*****Nivel 3*****"))
            setPDFListStatus(selected_products_mat[2])

            mDoc.add(Paragraph("\n*****Nivel 4*****"))
            setPDFListStatus(selected_products_mat[3])

            mDoc.add(Paragraph("\n*****Nivel 5*****"))
            setPDFListStatus(selected_products_mat[4])

            mDoc.add(Paragraph("\n*****Nivel 6*****"))
            setPDFListStatus(selected_products_mat[5])

            mDoc.close()
            Toast.makeText(context,"$mFileName.pdf\nis save to\n$mFilePath",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }

    //Función para guardar el pdf, usando la función  setPDFListCheckBox
    @RequiresApi(Build.VERSION_CODES.N)
    fun savePDFWithCBox(context: Context,all_products_mat: Array<Array<String?>>,checkbox_mat: Array<Array<Boolean>>) {
        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()
            mDoc.addAuthor("Juan")
            mDoc.add(Paragraph("==============================Lista de Compras==============================\n"))

            mDoc.add(Paragraph("\n*****Nivel 1*****"))
            setPDFListCheckBox(all_products_mat[0],checkbox_mat[0])

            mDoc.add(Paragraph("\n*****Nivel 2*****"))
            setPDFListCheckBox(all_products_mat[1],checkbox_mat[1])

            mDoc.add(Paragraph("\n*****Nivel 3*****"))
            setPDFListCheckBox(all_products_mat[2],checkbox_mat[2])

            mDoc.add(Paragraph("\n*****Nivel 4*****"))
            setPDFListCheckBox(all_products_mat[3],checkbox_mat[3])

            mDoc.add(Paragraph("\n*****Nivel 5*****"))
            setPDFListCheckBox(all_products_mat[4],checkbox_mat[4])

            mDoc.add(Paragraph("\n*****Nivel 6*****"))
            setPDFListCheckBox(all_products_mat[5],checkbox_mat[5])

            mDoc.close()
            Toast.makeText(context,"$mFileName.pdf\nis save to\n$mFilePath",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }




}