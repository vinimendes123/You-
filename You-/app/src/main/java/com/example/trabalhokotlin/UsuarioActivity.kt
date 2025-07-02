package com.example.trabalhokotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.trabalhokotlin.Data.AppDatabase
import kotlinx.coroutines.launch

class UsuarioActivity : AppCompatActivity() {
    private lateinit var tvNome: TextView
    private lateinit var tvYogaCount: TextView
    private lateinit var tvMeditationCount: TextView

    private val db by lazy { AppDatabase.getDatabase(this) }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }

        tvNome = findViewById(R.id.tvNome)
        tvYogaCount = findViewById(R.id.tvYogaCount)
        tvMeditationCount = findViewById(R.id.tvMeditationCount)

        val usuarioId = intent.getIntExtra("usuarioId", -1)

        if (usuarioId != -1) {
            carregarDados(usuarioId)
        } else {
            Toast.makeText(this, "Erro: usuário não identificado", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun carregarDados(usuarioId: Int) {
        lifecycleScope.launch {
            val usuario = db.usuarioDao().buscarPorId(usuarioId)
            val atividadesYoga = db.atividadeDao().contarPorTipo(usuarioId, "yoga")
            val tempoYoga = db.atividadeDao().somarDuracaoPorTipo(usuarioId, "yoga") ?: 0

            val atividadesMed = db.atividadeDao().contarPorTipo(usuarioId, "meditacao")
            val tempoMed = db.atividadeDao().somarDuracaoPorTipo(usuarioId, "meditacao") ?: 0

            tvNome.text = "Olá, ${usuario?.usuario ?: "usuário"}"
            tvYogaCount.text = "Yoga: ${atividadesYoga}x • ${tempoYoga}min"
            tvMeditationCount.text = "Meditação: ${atividadesMed}x • ${tempoMed}min"
        }
    }
}


