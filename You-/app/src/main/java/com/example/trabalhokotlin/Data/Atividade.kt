package com.example.trabalhokotlin.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "atividades")
data class Atividade(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val usuarioId: Int,
    val tipo: String, // "meditacao" ou "yoga"
    val duracaoMinutos: Int,
    val data: Long = System.currentTimeMillis()
)
