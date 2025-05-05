package com.example.appdevhackathon2025frontend.ui.screens

import android.R.attr.maxWidth
import android.R.attr.onClick
import android.R.attr.text
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.appdevhackathon2025frontend.ui.components.ImageCard
import com.example.appdevhackathon2025frontend.viewmodel.ItemViewModel

@Composable
fun ItemScreen(
    navController: NavController, itemId: String, itemViewModel: ItemViewModel = hiltViewModel()
) {
    val uiState = itemViewModel.uiState.collectAsState().value
    val showDialog = remember { mutableStateOf(false) }
    BackHandler {
        navController.popBackStack("homeScreen", inclusive = false)
    }

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .statusBarsPadding(),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
    ) {

        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.hsl(0f, 0.74f, 0.40f))
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = uiState.title ?: "Nameless Item",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center
                )
            }

            Card(
                Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageCard(
                        imageBitmap = uiState.imageBitmap,
                        modifier = Modifier
                            .wrapContentSize()
                            .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp),
                        onClick = { showDialog.value = true })
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            HighlightedTextBox(text = uiState.details)

            Spacer(modifier = Modifier.height(8.dp))

            HighlightedTextBox(text = uiState.description)

            Spacer(modifier = Modifier.height(8.dp))
            HighlightedTextBox(text = uiState.contact)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
    Box {
        if (showDialog.value && uiState.imageBitmap != null) {
            ImageDialog(
                imageBitmap = uiState.imageBitmap,
                onDismiss = { showDialog.value = false })
        }
    }
}

@Composable
fun ImageDialog(imageBitmap: ImageBitmap, onDismiss: () -> Unit) {

    var scale by remember { mutableFloatStateOf(1f) } // Variable to track zoom level

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = onDismiss
    ) {
        Surface(
            shape = RoundedCornerShape(12.dp),
            tonalElevation = 8.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .pointerInput(Unit) {
                        detectTransformGestures { _, pan, zoom, _ ->
                            scale *= zoom // Update the zoom level
                        }
                    }) {
                Image(
                    bitmap = imageBitmap,
                    contentDescription = "Zoomable image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 400.dp)
                        .graphicsLayer(scaleX = scale, scaleY = scale),
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}


@Composable
fun HighlightedTextBox(
    text: String?, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .border(
                2.dp,
                Color.hsl(0f, 0.74f, 0.40f),
                androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            ) // Purple border
            .padding(16.dp)
            .fillMaxWidth(),
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