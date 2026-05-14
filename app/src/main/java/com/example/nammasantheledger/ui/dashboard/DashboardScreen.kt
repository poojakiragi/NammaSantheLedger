package com.example.nammasantheledger.ui.dashboard

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nammasantheledger.model.Transaction
import com.example.nammasantheledger.viewmodel.MainViewModel

@Composable
fun DashboardScreen(

    mainViewModel: MainViewModel = viewModel(),

    onAddTransactionClick: () -> Unit,

    onReportsClick: () -> Unit

) {

    val context = LocalContext.current

    val income = mainViewModel.getTotalIncome()

    val expense = mainViewModel.getTotalExpense()

    val balance = mainViewModel.getBalance()

    val animatedBalance by animateFloatAsState(
        targetValue = balance.toFloat(),
        label = ""
    )

    val outstanding = mainViewModel.getTotalOutstanding()

    var searchText by remember {
        mutableStateOf("")
    }

    var searchedText by remember {
        mutableStateOf("")
    }

    val filteredTransactions =
        if (searchedText.isEmpty()) {

            mainViewModel.transactions

        } else {

            mainViewModel.transactions.filter {

                it.customerName.contains(
                    searchedText,
                    ignoreCase = true
                )
            }
        }

    LazyColumn(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FB))
            .padding(18.dp)

    ) {

        item {

            Text(
                text = "Welcome Back 👋",

                color = Color.Gray,

                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Namma Santhe Ledger",

                fontWeight = FontWeight.Bold,

                fontSize = 32.sp,

                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(32.dp))
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color(0xFF0F766E),
                                Color(0xFF14B8A6)
                            )
                        )
                    )
                    .padding(30.dp)
            ) {

                Column {

                    Text(
                        text = "Total Balance",

                        color = Color.White.copy(alpha = 0.9f),

                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = "₹${animatedBalance.toInt()}",

                        color = Color.White,

                        fontWeight = FontWeight.ExtraBold,

                        fontSize = 44.sp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Manage your santhe business digitally",

                        color = Color.White.copy(alpha = 0.75f)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {

                FinanceCard(
                    title = "Income",
                    amount = income,
                    color = Color(0xFF16A34A),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(14.dp))

                FinanceCard(
                    title = "Expense",
                    amount = expense,
                    color = Color(0xFFDC2626),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            FinanceCard(
                title = "Outstanding Due",
                amount = outstanding,
                color = Color(0xFFF59E0B),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(

                value = searchText,

                onValueChange = {

                    searchText = it

                },

                label = {

                    Text("Search Customer")

                },

                leadingIcon = {

                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },

                shape = RoundedCornerShape(22.dp),

                modifier = Modifier.fillMaxWidth(),

                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF0F766E),
                    focusedLabelColor = Color(0xFF0F766E)
                )
            )

            Spacer(modifier = Modifier.height(14.dp))

            Button(

                onClick = {

                    searchedText = searchText

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp),

                shape = RoundedCornerShape(20.dp)

            ) {

                Text(
                    text = "Search",

                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Button(

                onClick = {

                    onAddTransactionClick()

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(62.dp),

                shape = RoundedCornerShape(24.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0F766E)
                )

            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Add Transaction",

                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedButton(

                onClick = {

                    onReportsClick()

                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),

                shape = RoundedCornerShape(22.dp)

            ) {

                Text(
                    text = "View Reports",

                    fontSize = 18.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Customer Ledger",

                fontWeight = FontWeight.Bold,

                fontSize = 26.sp,

                color = Color(0xFF111827)
            )

            Spacer(modifier = Modifier.height(16.dp))
        }

        items(filteredTransactions) { transaction ->

            PremiumTransactionCard(

                transaction = transaction,

                onMarkPaid = {

                    mainViewModel.markAsPaid(transaction)

                },

                onSendReminder = {

                    val message =
                        "Hello ${transaction.customerName}, your pending due is ₹${transaction.amount}. Please clear it soon."

                    val intent = Intent(
                        Intent.ACTION_VIEW
                    )

                    intent.data = Uri.parse(
                        "https://wa.me/?text=${Uri.encode(message)}"
                    )

                    context.startActivity(intent)
                }
            )
        }
    }
}

@Composable
fun FinanceCard(

    title: String,

    amount: Double,

    color: Color,

    modifier: Modifier

) {

    Card(

        modifier = modifier,

        shape = RoundedCornerShape(26.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        )

    ) {

        Column(
            modifier = Modifier.padding(22.dp)
        ) {

            Text(
                text = title,

                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "₹$amount",

                fontWeight = FontWeight.ExtraBold,

                color = color,

                fontSize = 26.sp
            )
        }
    }
}

@Composable
fun PremiumTransactionCard(

    transaction: Transaction,

    onMarkPaid: () -> Unit,

    onSendReminder: () -> Unit

) {

    var visible by remember {

        mutableStateOf(false)

    }

    LaunchedEffect(Unit) {

        visible = true
    }

    AnimatedVisibility(

        visible = visible,

        enter = fadeIn() +
                slideInVertically(
                    initialOffsetY = { it / 2 }
                )

    ) {

        val typeColor =
            when (transaction.type) {

                "Income" -> Color(0xFF16A34A)

                "Expense" -> Color(0xFFDC2626)

                else -> Color(0xFFF59E0B)
            }

        Card(

            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),

            shape = RoundedCornerShape(30.dp),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )

        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = transaction.customerName,

                            fontWeight = FontWeight.Bold,

                            fontSize = 22.sp
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = transaction.title,

                            color = Color.Gray,

                            fontSize = 14.sp
                        )
                    }

                    Card(

                        shape = RoundedCornerShape(14.dp),

                        colors = CardDefaults.cardColors(
                            containerColor =
                                typeColor.copy(alpha = 0.12f)
                        )

                    ) {

                        Text(

                            text = transaction.type,

                            color = typeColor,

                            modifier = Modifier.padding(
                                horizontal = 14.dp,
                                vertical = 8.dp
                            ),

                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "₹${transaction.amount}",

                    fontWeight = FontWeight.ExtraBold,

                    fontSize = 34.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Note: ${transaction.note}",

                    color = Color(0xFF6B7280),

                    fontSize = 14.sp
                )

                if (transaction.type == "Udhar") {

                    Spacer(modifier = Modifier.height(20.dp))

                    if (transaction.isPaid) {

                        Card(

                            colors = CardDefaults.cardColors(
                                containerColor =
                                    Color(0xFFDCFCE7)
                            ),

                            shape = RoundedCornerShape(14.dp)

                        ) {

                            Text(
                                text = "Payment Completed",

                                color = Color(0xFF166534),

                                fontWeight = FontWeight.Bold,

                                modifier = Modifier.padding(
                                    horizontal = 16.dp,
                                    vertical = 10.dp
                                )
                            )
                        }

                    } else {

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {

                            Button(

                                onClick = {

                                    onMarkPaid()

                                },

                                modifier = Modifier.weight(1f),

                                shape = RoundedCornerShape(18.dp),

                                colors = ButtonDefaults.buttonColors(
                                    containerColor =
                                        Color(0xFF0F766E)
                                )

                            ) {

                                Text("Mark Paid")
                            }

                            Spacer(
                                modifier = Modifier.width(12.dp)
                            )

                            OutlinedButton(

                                onClick = {

                                    onSendReminder()

                                },

                                modifier = Modifier.weight(1f),

                                shape = RoundedCornerShape(18.dp)

                            ) {

                                Icon(
                                    imageVector = Icons.Default.Send,
                                    contentDescription = null
                                )

                                Spacer(
                                    modifier = Modifier.width(6.dp)
                                )

                                Text("Reminder")
                            }
                        }
                    }
                }
            }
        }
    }
}