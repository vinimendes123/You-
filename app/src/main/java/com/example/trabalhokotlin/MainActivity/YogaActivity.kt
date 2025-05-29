package com.example.trabalhokotlin
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView


class YogaActivity : AppCompatActivity() {
    private lateinit var tvTimer: TextView
    private lateinit var btnNext: Button
    private var countDownTimer: CountDownTimer? = null
    private var timeLeftInMillis = 30000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        tvTimer = findViewById(R.id.tvTimer)
        btnNext = findViewById(R.id.btnNext)

        startTimer()

        btnNext.setOnClickListener {
            countDownTimer?.cancel()
            startTimer()
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(timeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                tvTimer.text = secondsLeft.toString()
            }

            override fun onFinish() {
                btnNext.performClick()
            }
        }.start()

        val btnVoltar = findViewById<ImageButton>(R.id.btnVoltar)
        btnVoltar.setOnClickListener {
            finish() // Encerra a Activity atual e volta à anterior
        }

    }

    override fun onDestroy() {
        countDownTimer?.cancel()
        super.onDestroy()
    }


    }

