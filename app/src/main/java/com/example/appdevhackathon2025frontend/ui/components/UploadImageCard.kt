package com.example.appdevhackathon2025frontend.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UploadImageCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(3.dp, Color.hsl(0f, 0.74f, 0.40f)),
        color = Color.White,
        modifier = modifier,
        onClick = onClick,
        shadowElevation = 2.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            Text(
                text = "( + )",
                modifier = Modifier.align(Alignment.Center),
                fontWeight = FontWeight.Bold,
                color = Color.hsl(0f, 0.74f, 0.40f),

                fontSize = 26.sp
            )
        }
    }
}

@Preview
@Composable
fun UploadImageCardPreview() {
    UploadImageCard {}
}
