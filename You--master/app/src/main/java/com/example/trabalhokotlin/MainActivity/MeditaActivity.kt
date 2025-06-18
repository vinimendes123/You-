package com.example.trabalhokotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.widget.Button
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton

class MeditaActivity : AppCompatActivity() {

    private lateinit var timerText: TextView
    private lateinit var instructionText: TextView
    private lateinit var btnParar: Button
    private lateinit var btnPlay: Button

    private val handler = Handler(Looper.getMainLooper())
    private var seconds = 5
    private var isInhale = true
    private var isRunning = false

    private val breathingRunnable = object : Runnable {
        override fun run() {
            timerText.text = seconds.toString()
            if (seconds == 0) {
                isInhale = !isInhale
                instructionText.text = if (isInhale) "Inspire..." else "Expire..."
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

        // Timer começa parado, então não iniciamos aqui

        btnPlay.setOnClickListener {
            if (!isRunning) {
                handler.post(breathingRunnable)
                isRunning = true
            }
        }

        btnParar.setOnClickListener {
            handler.removeCallbacks(breathingRunnable)
            isRunning = false
        }

        val btnBack = findViewById<ImageButton>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Encerra a activity atual e retorna à anterior
        }


    }

}
