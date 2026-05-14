package com.example.nammasantheledger.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")

data class Transaction(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val customerName: String,

    val title: String,

    val amount: Double,

    val type: String,

    val note: String = "",

    val isPaid: Boolean = false
)