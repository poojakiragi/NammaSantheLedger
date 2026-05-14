package com.example.nammasantheledger.ui.reports

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nammasantheledger.model.Transaction
import com.example.nammasantheledger.viewmodel.MainViewModel

@Composable
fun ReportsScreen(

    mainViewModel: MainViewModel = viewModel(),

    onBackClick: () -> Unit

) {

    val income = mainViewModel.getTotalIncome()

    val expense = mainViewModel.getTotalExpense()

    val outstanding = mainViewModel.getTotalOutstanding()

    val totalTransactions =
        mainViewModel.transactions.size

    val paidCount =
        mainViewModel.transactions.count {

            it.isPaid

        }

    val pendingCount =
        mainViewModel.transactions.count {

            it.type == "Udhar" && !it.isPaid

        }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Business Summary",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        SummaryCard(
            title = "Total Income",
            value = "₹$income",
            color = Color(0xFF16A34A)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SummaryCard(
            title = "Total Expense",
            value = "₹$expense",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(12.dp))

        SummaryCard(
            title = "Outstanding Due",
            value = "₹$outstanding",
            color = Color(0xFFFF9800)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SummaryCard(
            title = "Total Transactions",
            value = "$totalTransactions",
            color = Color(0xFF2563EB)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SummaryCard(
            title = "Paid Customers",
            value = "$paidCount",
            color = Color(0xFF16A34A)
        )

        Spacer(modifier = Modifier.height(12.dp))

        SummaryCard(
            title = "Pending Udhari",
            value = "$pendingCount",
            color = Color.Red
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Daily Summary",

                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text =
                        "Today you sold for ₹$income and pending dues are ₹$outstanding"
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(

            onClick = {

                onBackClick()

            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Back")
        }
    }
}

@Composable
fun SummaryCard(

    title: String,

    value: String,

    color: Color

) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(title)

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = value,

                color = color,

                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}