package com.example.nammasantheledger.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val AppColorScheme = darkColorScheme(

    primary = PrimaryGreen,

    secondary = LightGreen

)

@Composable
fun NammaSantheLedgerTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(

        colorScheme = AppColorScheme,

        content = content
    )
}