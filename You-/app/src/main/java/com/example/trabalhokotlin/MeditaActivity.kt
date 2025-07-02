package com.example.trabalhokotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.trabalhokotlin.Data.AppDatabase
import com.example.trabalhokotlin.Data.Atividade
import kotlinx.coroutines.launch

class MeditaActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var instructionText: TextView
    private lateinit var btnParar: Button
    private lateinit var btnPlay: Button

    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 5
    private var isInhale = true
    private var isRunning = false
    private var ciclosConcluidos = 0
    private val ciclosMaximos = 12

    private val breathingRunnable = object : Runnable {
        override fun run() {
            timerText.text = seconds.toString()

            if (seconds == 0) {
                isInhale = !isInhale
                instructionText.text = if (isInhale) "Inspire..." else "Expire..."

                if (!isInhale) ciclosConcluidos++

                if (ciclosConcluidos >= ciclosMaximos) {
                    finalizarSessao()
                    return
                }

                seconds = 5
            } else {
                seconds--
            }

            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medita)

        timerText = findViewById(R.id.tvTimer)
        instructionText = findViewById(R.id.tvInstrução)
        btnParar = findViewById(R.id.btnParar)
        btnPlay = findViewById(R.id.btnPlay)

        btnPlay.setOnClickListener {
            if (!isRunning) {
                handler.post(breathingRunnable)
                isRunning = true
                btnPlay.isEnabled = false
                btnParar.isEnabled = true
            }
        }

        btnParar.setOnClickListener {
            handler.removeCallbacks(breathingRunnable)
            isRunning = false
            btnPlay.isEnabled = true
            btnParar.isEnabled = false
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun finalizarSessao() {
        handler.removeCallbacks(breathingRunnable)
        isRunning = false

        instructionText.text = "Meditação concluída!"
        timerText.text = ""

        val usuarioId = intent.getIntExtra("usuarioId", -1)
        val minutos = (ciclosMaximos * 10) / 60

        if (usuarioId != -1) {
            lifecycleScope.launch {
                AppDatabase.getDatabase(this@MeditaActivity).atividadeDao().inserir(
                    Atividade(
                        usuarioId = usuarioId,
                        tipo = "meditacao",
                        duracaoMinutos = minutos
                    )
                )
            }
        }

        Toast.makeText(this, "Sessão salva com sucesso", Toast.LENGTH_SHORT).show()
        finish()
    }

}
