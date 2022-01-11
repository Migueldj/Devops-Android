package com.example.myrefri

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.system.measureTimeMillis

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    private fun updateUI(user: FirebaseUser?) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = Firebase.auth
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.btn_login) //PARA EL BOTÓN INGRESAR
        button.setOnClickListener {
            //val intent = Intent(this,MainActivity::class.java)
            //startActivity(intent)
            val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()

            if(email.isEmpty()){
                Toast.makeText(
                    baseContext, "Ingrese Email",
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(null)
            }
            if(password.isEmpty()){
                Toast.makeText(
                    baseContext, "Ingrese Password",
                    Toast.LENGTH_SHORT
                ).show()
                updateUI(null)
            }


            //Eliminar estas 2 líneas de código
            //val intent = Intent(this,Status::class.java)
            //startActivity(intent)

            //Descomentar estas líneas de código

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        //updateUI(user)
                        val intent = Intent(this, Status::class.java)
                        startActivity(intent)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Usuario o contraseña incorrecto",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }

        val buttonRegister = findViewById<Button>(R.id.button_register) //PARA EL BOTÓN REGISTRAR
        buttonRegister .setOnClickListener {
            val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
            val email = editTextTextEmailAddress.text.toString()
            val password = editTextTextPassword.text.toString()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        Toast.makeText(baseContext, "Usuario creado con éxito!",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Este usuario ya existe",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }

    }
}