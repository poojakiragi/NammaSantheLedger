package com.example.nammasantheledger.ui.addtransaction

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nammasantheledger.model.Transaction
import com.example.nammasantheledger.viewmodel.MainViewModel

@Composable
fun AddTransactionScreen(

    mainViewModel: MainViewModel = viewModel(),

    onTransactionAdded: () -> Unit

) {

    var customerName by remember {

        mutableStateOf("")

    }

    var title by remember {

        mutableStateOf("")

    }

    var amount by remember {

        mutableStateOf("")

    }

    var type by remember {

        mutableStateOf("Income")

    }

    var note by remember {

        mutableStateOf("")

    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {

        Text(
            text = "Add Transaction",

            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(

            value = customerName,

            onValueChange = {

                customerName = it

            },

            label = {

                Text("Customer Name")

            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = title,

            onValueChange = {

                title = it

            },

            label = {

                Text("Transaction Title")

            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = amount,

            onValueChange = {

                amount = it

            },

            label = {

                Text("Amount")

            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(

            value = note,

            onValueChange = {

                note = it

            },

            label = {

                Text("Note")

            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("Transaction Type")

        Spacer(modifier = Modifier.height(10.dp))

        Row {

            Button(
                onClick = {

                    type = "Income"

                }
            ) {

                Text("Income")

            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {

                    type = "Expense"

                }
            ) {

                Text("Expense")

            }

            Spacer(modifier = Modifier.width(10.dp))

            Button(
                onClick = {

                    type = "Udhar"

                }
            ) {

                Text("Udhar")

            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(

            onClick = {

                if (
                    customerName.isNotEmpty() &&
                    title.isNotEmpty() &&
                    amount.isNotEmpty()
                ) {

                    mainViewModel.addTransaction(

                        Transaction(

                            customerName = customerName,

                            title = title,

                            amount = amount.toDouble(),

                            type = type,

                            note = note

                        )
                    )

                    onTransactionAdded()

                }
            },

            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Save Transaction")

        }
    }
}