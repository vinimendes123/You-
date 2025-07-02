package com.example.trabalhokotlin.Data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface AtividadeDAO {

    @Insert
    suspend fun inserir(atividade: Atividade)

    @Query("SELECT * FROM atividades WHERE usuarioId = :usuarioId")
    suspend fun listarPorUsuario(usuarioId: Int): List<Atividade>

    @Query("SELECT COUNT(*) FROM atividades WHERE usuarioId = :usuarioId AND tipo = :tipo")
    suspend fun contarPorTipo(usuarioId: Int, tipo: String): Int

    @Query("SELECT SUM(duracaoMinutos) FROM atividades WHERE usuarioId = :usuarioId AND tipo = :tipo")
    suspend fun somarDuracaoPorTipo(usuarioId: Int, tipo: String): Int?
}