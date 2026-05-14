package com.example.nammasantheledger

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nammasantheledger.ui.addtransaction.AddTransactionScreen
import com.example.nammasantheledger.ui.dashboard.DashboardScreen
import com.example.nammasantheledger.ui.reports.ReportsScreen
import com.example.nammasantheledger.ui.theme.NammaSantheLedgerTheme
import com.example.nammasantheledger.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            NammaSantheLedgerTheme {

                val mainViewModel: MainViewModel = viewModel()

                var currentScreen by remember {

                    mutableStateOf("dashboard")

                }

                when (currentScreen) {

                    "dashboard" -> {

                        DashboardScreen(

                            mainViewModel = mainViewModel,

                            onAddTransactionClick = {

                                currentScreen = "add"

                            },

                            onReportsClick = {

                                currentScreen = "reports"

                            }
                        )
                    }

                    "add" -> {

                        AddTransactionScreen(

                            mainViewModel = mainViewModel,

                            onTransactionAdded = {

                                currentScreen = "dashboard"

                            }
                        )
                    }

                    "reports" -> {

                        ReportsScreen(

                            mainViewModel = mainViewModel,

                            onBackClick = {

                                currentScreen = "dashboard"

                            }
                        )
                    }
                }
            }
        }
    }
}