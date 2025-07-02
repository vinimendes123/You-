package com.example.trabalhokotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.trabalhokotlin.Data.Usuario
import com.example.trabalhokotlin.Data.AppDatabase
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var usuarioInput: EditText
    private lateinit var senhaInput: EditText
    private lateinit var btnEntrar: Button

    private val db by lazy { AppDatabase.getDatabase(this) }
    private val usuarioDao by lazy { db.usuarioDao() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usuarioInput = findViewById(R.id.etUsername)
        senhaInput = findViewById(R.id.etPassword)
        btnEntrar = findViewById(R.id.btnEntrar)

        lifecycleScope.launch {
            val usuariosExistentes = usuarioDao.listarTodos()
            if (usuariosExistentes.isEmpty()) {
                val usuarioTeste = Usuario(
                    usuario = "Brian_OConner",
                    senha = "1327"
                )
                usuarioDao.inserir(usuarioTeste)
            }
        }

        btnEntrar.setOnClickListener {
            val usuario = usuarioInput.text.toString()
            val senha = senhaInput.text.toString()

            lifecycleScope.launch {
                val usuario = usuarioDao.autenticar(usuario, senha)
                if (usuario != null) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("usuarioId", usuario.id)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this@LoginActivity,
                        "Usuário ou senha inválidos.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}