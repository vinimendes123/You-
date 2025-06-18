package com.example.trabalhokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhokotlin.data.AppDatabase

class UsuarioActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)


        val db = AppDatabase.getDatabase(this)
        val dao = db.usuarioDao()

        if (dao != null) {
            Log.d("UsuarioActivity", " Conectado ao banco de dados com sucesso!")
        } else {
            Log.e("UsuarioActivity", " Falha ao conectar ao banco de dados.")
        }
        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
