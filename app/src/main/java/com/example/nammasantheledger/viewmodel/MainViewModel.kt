package com.example.nammasantheledger.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.nammasantheledger.data.AppDatabase
import com.example.nammasantheledger.model.Transaction
import kotlinx.coroutines.launch

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private val db =
        Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            "namma_santhe_db"
        ).build()

    private val dao =
        db.transactionDao()

    var transactions =
        mutableStateListOf<Transaction>()
        private set

    init {

        loadTransactions()
    }

    private fun loadTransactions() {

        viewModelScope.launch {

            val savedTransactions =
                dao.getAllTransactions()

            transactions.clear()

            transactions.addAll(savedTransactions)
        }
    }

    fun addTransaction(
        transaction: Transaction
    ) {

        viewModelScope.launch {

            dao.insertTransaction(transaction)

            loadTransactions()
        }
    }

    fun deleteTransaction(
        transaction: Transaction
    ) {

        viewModelScope.launch {

            dao.deleteTransaction(transaction)

            loadTransactions()
        }
    }

    fun markAsPaid(
        transaction: Transaction
    ) {

        viewModelScope.launch {

            val updatedTransaction =
                transaction.copy(
                    isPaid = true
                )

            dao.updateTransaction(
                updatedTransaction
            )

            loadTransactions()
        }
    }

    fun getTotalIncome(): Double {

        val normalIncome =
            transactions
                .filter {

                    it.type == "Income"
                }
                .sumOf {

                    it.amount
                }

        val paidUdhar =
            transactions
                .filter {

                    it.type == "Udhar" &&
                            it.isPaid
                }
                .sumOf {

                    it.amount
                }

        return normalIncome + paidUdhar
    }

    fun getTotalExpense(): Double {

        return transactions
            .filter {

                it.type == "Expense"
            }
            .sumOf {

                it.amount
            }
    }

    fun getTotalOutstanding(): Double {

        return transactions
            .filter {

                it.type == "Udhar" &&
                        !it.isPaid
            }
            .sumOf {

                it.amount
            }
    }

    fun getBalance(): Double {

        return getTotalIncome() -
                getTotalExpense()
    }
}