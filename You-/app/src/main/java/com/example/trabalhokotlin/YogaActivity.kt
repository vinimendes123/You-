package com.example.trabalhokotlin
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.trabalhokotlin.Data.AppDatabase
import com.example.trabalhokotlin.Data.Atividade
import kotlinx.coroutines.launch


class YogaActivity : AppCompatActivity() {
    private lateinit var imgPose: ImageView
    private lateinit var tvPoseName: TextView
    private lateinit var tvInstructions: TextView
    private lateinit var tvTimer: TextView
    private lateinit var btnNext: Button

    private val imagensYoga = listOf(
        R.drawable.cobra,
        R.drawable.cachorro_olhando_baixo,
        R.drawable.postua_arvore,
        R.drawable.guerreiro,
        R.drawable.montanha
    ).shuffled()

    private var indiceAtual = 0
    private val duracaoSegundos = 30
    private var cronometro: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        imgPose = findViewById(R.id.imgPose)
        tvPoseName = findViewById(R.id.tvPoseName)
        tvInstructions = findViewById(R.id.tvInstructions)
        tvTimer = findViewById(R.id.tvTimer)
        btnNext = findViewById(R.id.btnNext)

        btnNext.setOnClickListener {
            cronometro?.cancel()
            proximaPosicao()
        }

        iniciarPosicao(imagensYoga[indiceAtual])
    }

    private fun iniciarPosicao(resourceId: Int) {
        imgPose.setImageResource(resourceId)

        val nome = resources.getResourceEntryName(resourceId)
            .replace("_", " ")
            .replaceFirstChar { it.uppercase() }

        tvPoseName.text = nome
        tvInstructions.text = gerarInstrucao(nome)

        cronometro = object : CountDownTimer(duracaoSegundos * 1000L, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                tvTimer.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                proximaPosicao()
            }
        }.start()
    }

    private fun proximaPosicao() {
        indiceAtual++
        if (indiceAtual < imagensYoga.size) {
            iniciarPosicao(imagensYoga[indiceAtual])
        } else {
            finalizarSessao()
        }
    }

    private fun finalizarSessao() {
        Toast.makeText(this, "Sessão finalizada!", Toast.LENGTH_SHORT).show()

        val duracaoTotalMinutos = (duracaoSegundos * imagensYoga.size) / 60
        val usuarioId = intent.getIntExtra("usuarioId", -1)

        if (usuarioId != -1) {
            lifecycleScope.launch {
                AppDatabase.getDatabase(this@YogaActivity).atividadeDao().inserir(
                    Atividade(
                        usuarioId = usuarioId,
                        tipo = "yoga",
                        duracaoMinutos = duracaoTotalMinutos
                    )
                )
            }
        }

        finish()
    }

    private fun gerarInstrucao(nome: String): String {
        return when (nome.lowercase()) {
            "postura cobra" -> "Deite-se de barriga para baixo, mãos no chão e eleve o peito."
            "cachorro olhando baixo" -> "Levante o quadril formando um V invertido."
            "postura arvore" -> "Fique em pé, apoie um pé na coxa oposta e junte as mãos."
            "guerreiro" -> "Uma perna à frente flexionada e braços estendidos."
            "montanha" -> "Fique em pé com os pés juntos e coluna ereta."
            else -> "Mantenha a postura e respire profundamente."
        }
    }
}

