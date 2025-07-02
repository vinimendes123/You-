package com.example.trabalhokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var usuarioId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usuarioId = intent.getIntExtra("usuarioId", -1)

        findViewById<Button>(R.id.btnUsuario).setOnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("usuarioId", usuarioId)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnYoga).setOnClickListener {
            val intent = Intent(this, YogaActivity::class.java)
            intent.putExtra("usuarioId", usuarioId)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnMeditacao).setOnClickListener {
            val intent = Intent(this, MeditaActivity::class.java)
            intent.putExtra("usuarioId", usuarioId)
            startActivity(intent)
        }
    }
}
