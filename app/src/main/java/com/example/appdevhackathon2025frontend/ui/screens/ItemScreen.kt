package com.example.appdevhackathon2025frontend.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appdevhackathon2025frontend.ui.components.ImageCard
import com.example.appdevhackathon2025frontend.viewmodel.ItemViewModel

@Composable
fun ItemScreen(
    navController: NavController,
    itemId: String,
    itemViewModel: ItemViewModel = hiltViewModel()
) {
    val uiState = itemViewModel.uiState.collectAsState().value
    BackHandler {
        navController.popBackStack("homeScreen", inclusive = false)
    }

    Surface(
        modifier = Modifier.fillMaxHeight(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(16.dp))
            ImageCard(
                imageBitmap = uiState.imageBitmap, modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight(0.2f)
            )


            Spacer(modifier = Modifier.height(16.dp))

            HighlightedTextBox(text = uiState.details)

            Spacer(modifier = Modifier.height(8.dp))

            HighlightedTextBox(text = uiState.description)

            Spacer(modifier = Modifier.height(8.dp))
            HighlightedTextBox(text = uiState.contact)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun HighlightedTextBox(
    text: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                2.dp,
                Color(0xFF800080),
                androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) // Purple border
            .padding(16.dp)
            .fillMaxWidth()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (text != null) {
            Text(
                text = text,
                color = Color.Black,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Preview
@Composable
fun ItemScreenPreview() {
}