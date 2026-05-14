package com.example.nammasantheledger.data

import androidx.room.*
import com.example.nammasantheledger.model.Transaction

@Dao
interface TransactionDao {

    @Insert
    suspend fun insertTransaction(
        transaction: Transaction
    )

    @Delete
    suspend fun deleteTransaction(
        transaction: Transaction
    )

    @Update
    suspend fun updateTransaction(
        transaction: Transaction
    )

    @Query("SELECT * FROM transactions")
    suspend fun getAllTransactions(): List<Transaction>
}