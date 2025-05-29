package com.example.trabalhokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalhokotlin.UsuarioActivity
import com.example.trabalhokotlin.YogaActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnUsuario).setOnClickListener {
            startActivity(Intent(this, UsuarioActivity::class.java))
        }

        findViewById<Button>(R.id.btnYoga).setOnClickListener {
            startActivity(Intent(this, YogaActivity::class.java))
        }

        findViewById<Button>(R.id.btnMeditacao).setOnClickListener {
            startActivity(Intent(this, MeditaActivity::class.java))
        }
    }
}
