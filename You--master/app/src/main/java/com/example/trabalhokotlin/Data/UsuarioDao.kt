package com.example.trabalhokotlin.data

import androidx.room.*

@Dao
interface UsuarioDao {
    @Insert
    suspend fun inserir(usuario: Usuario)

    @Query("SELECT * FROM usuarios")
    suspend fun listarTodos(): List<Usuario>
}
