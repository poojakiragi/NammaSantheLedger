package com.example.nammasantheledger.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nammasantheledger.model.Transaction

@Database(
    entities = [Transaction::class],
    version = 1
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun transactionDao(): TransactionDao
}