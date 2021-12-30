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


class GeneratePDF {
    var mDoc: Document =Document()
    @RequiresApi(Build.VERSION_CODES.N)
    var mFileName = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(System.currentTimeMillis())
    var mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).toString()+"/"+mFileName+".pdf"  // Environment.DIRECTORY_DOWNLOADS+"/"+mFileName+".pdf"

    fun product_list(array: Array<String?>){
        for (i in (0 until array.size)){
            var produto:String? = array[i]
            if(produto=="null"||produto==""||produto==null) {
                mDoc.add(Paragraph(""))
            } else{
                mDoc.add(Paragraph("-"+produto))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
     fun savePDF(context: Context,nivel_list:Array<Array<String?>>) {
        try {
            PdfWriter.getInstance(mDoc,FileOutputStream(mFilePath))
            mDoc.open()
            mDoc.addAuthor("Juan")
            mDoc.add(Paragraph("==============================Lista de Compras==============================\n"))

            mDoc.add(Paragraph("\n*****Nivel 1*****"))
            product_list(nivel_list[0])

            mDoc.add(Paragraph("\n*****Nivel 2*****"))
            product_list(nivel_list[1])

            mDoc.add(Paragraph("\n*****Nivel 3*****"))
            product_list(nivel_list[2])

            mDoc.add(Paragraph("\n*****Nivel 4*****"))
            product_list(nivel_list[3])

            mDoc.add(Paragraph("\n*****Nivel 5*****"))
            product_list(nivel_list[4])

            mDoc.add(Paragraph("\n*****Nivel 6*****"))
            product_list(nivel_list[5])

            mDoc.close()
            Toast.makeText(context,"$mFileName.pdf\nis save to\n$mFilePath",Toast.LENGTH_SHORT).show()
        }catch (e:Exception){
            Toast.makeText(context,e.message,Toast.LENGTH_SHORT).show()
        }
    }
}