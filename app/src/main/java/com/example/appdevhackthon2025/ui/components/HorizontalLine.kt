package com.example.appdevhackthon2025.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HorizontalLine(
    modifier: Modifier = Modifier
) {
    Spacer(
        modifier = modifier
            .padding(horizontal = 24.dp, vertical = 10.dp)
            .background(Color.Gray)
            .height(1.dp)
            .fillMaxWidth()
    )
}