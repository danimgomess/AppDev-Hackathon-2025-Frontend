package com.example.appdevhackthon2025.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appdevhackthon2025.viewmodel.ItemViewModel

@Composable
fun ItemScreen(itemId: String, itemViewModel: ItemViewModel = hiltViewModel()) {
    val uiState = itemViewModel.uiState.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
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

            Text(
                text = uiState.details,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = uiState.description,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = uiState.contact,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
fun IS() {

    Surface(
        modifier = Modifier.fillMaxHeight(),
        shape = RoundedCornerShape(8.dp),
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

            Text(
                text = "uiState.details",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "uiState.description",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "uiState.contact",
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Preview
@Composable
fun ItemScreenPreview() {
    IS()
}