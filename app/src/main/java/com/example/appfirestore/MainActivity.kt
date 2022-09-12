package com.example.appfirestore

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Access a Cloud Firestore instance from your Activity

        val db = Firebase.firestore

        val editNome: EditText = findViewById(R.id.editNome)
        val editEndereco: EditText = findViewById(R.id.editEndereco)
        val editBairro: EditText = findViewById(R.id.editBairro)
        val editCep: EditText = findViewById(R.id.editCep)
        val btnCadastrar: Button = findViewById(R.id.btncadastrar)

        btnCadastrar.setOnClickListener {

            // Create a new user with a first and last name
            val pessoa = hashMapOf(
                "nome" to editNome.text.toString(),
                "endereco" to editEndereco.text.toString(),
                "bairro" to editBairro.text.toString(),
                "cep" to editCep.text.toString()
            )

            // Add a new document with a generated ID
            db.collection("cadastro")
                .add(pessoa)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error adding document", e)
                }
        }

    }
}