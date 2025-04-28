package com.example.appdevhackthon2025.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.appdevhackthon2025.viewmodel.FormViewModel

@Composable
fun FormScreen(formViewModel: FormViewModel = hiltViewModel()) {
    val uiState = formViewModel.uiState.collectAsState().value

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
                    .clickable {
                        // come back here!
                    }
            ) {
                AnimatedContent(targetState = uiState.picture, label = "picture") { image ->
                    when (image) {
                        null -> Box(
                            modifier = Modifier
                                .background(Color.Gray)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                        else -> Image(
                            bitmap = image,
                            contentDescription = "Selected image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = uiState.name,
                onValueChange = { formViewModel.updateName(it) },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = { formViewModel.updateEmail(it) },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.phone,
                onValueChange = { formViewModel.updatePhone(it) },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.title,
                onValueChange = { formViewModel.updateTitle(it) },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.location,
                onValueChange = { formViewModel.updateLocation(it) },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.description,
                onValueChange = { formViewModel.updateDescription(it) },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.timeFound,
                onValueChange = { formViewModel.updateTimeFound(it) },
                label = { Text("Time Found") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    formViewModel.submitForm()
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun FS() {

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
                    .clickable {
                        // come back here!
                    }
            ) {
                AnimatedContent(targetState = null, label = "picture") { image ->
                    when (image) {
                        null -> Box(
                            modifier = Modifier
                                .background(Color.Gray)
                                .fillMaxWidth()
                                .fillMaxHeight()
                        )
                        else -> Image(
                            bitmap = image,
                            contentDescription = "Selected image",
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = "uiState.name",
                onValueChange = { "formViewModel.updateName(it)" },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.email",
                onValueChange = { "formViewModel.updateEmail(it)" },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.phone",
                onValueChange = { "formViewModel.updatePhone(it)" },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.title",
                onValueChange = { "formViewModel.updateTitle(it)" },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.location",
                onValueChange = { "formViewModel.updateLocation(it)" },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.description",
                onValueChange = { "formViewModel.updateDescription(it)" },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.timeFound",
                onValueChange = { "formViewModel.updateTimeFound(it)" },
                label = { Text("Time Found") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}


@Preview
@Composable
fun FormScreenPreview() {
    FS()
}
