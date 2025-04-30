package com.example.appdevhackathon2025frontend.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ItemScreen(
    navController: NavController,
    itemId: String,
//    itemViewModel: ItemViewModel = hiltViewModel()
) {
//    val uiState = itemViewModel.uiState.collectAsState().value
    BackHandler {
        navController.popBackStack("homeScreen", inclusive = false)
    }

    Surface(
        modifier = Modifier.fillMaxHeight(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
    ) {

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .height(220.dp)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .clickable(onClick = {
                        //Should this be passed in?
                    }),
            ) {
                val image = null //replace with method to get image from itemId. Needs backing repo
                AnimatedContent(targetState = image, label = "image loading") { response ->
                    when (response) {
                        null -> {
                            Box(
                                modifier = Modifier
                                    .background(Color.Gray)
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                            )
                        }

                        else -> {
                            Image(
                                bitmap = response,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            val modifier = Modifier.background(color = Color.LightGray)

            HighlightedTextBox(
//                    text = "uiState.details",
                text = "details"
            )


            Spacer(modifier = Modifier.height(8.dp))

            HighlightedTextBox(
//                text = "uiState.description",
                text = "description"
            )

            Spacer(modifier = Modifier.height(8.dp))
            HighlightedTextBox(
//                text = "uiState.contact",
                text = "contact"
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun HighlightedTextBox(
    text: String,
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
        Text(
            text = text,
            color = Color.Black,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview
@Composable
fun ItemScreenPreview() {
}