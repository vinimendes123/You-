package com.example.trabalhokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class UsuarioActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Encerra a activity atual e retorna à anterior
        }
    }
}


