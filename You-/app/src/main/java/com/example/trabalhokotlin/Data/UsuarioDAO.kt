package com.example.trabalhokotlin.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UsuarioDAO {
    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    suspend fun listarTodos(): List<Usuario>

    @Query("SELECT * FROM usuarios WHERE usuario = :usuario AND senha = :senha")
    suspend fun autenticar(usuario: String, senha: String): Usuario?

    @Query("SELECT * FROM usuarios WHERE id = :id")
    suspend fun buscarPorId(id: Int): Usuario?
}