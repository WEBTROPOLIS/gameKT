package com.example.kotlingame.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity("data_table")
data class Datos (
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int =0,
    val nameGame: String,
    val nameReal: String,
    val age:Int

        )