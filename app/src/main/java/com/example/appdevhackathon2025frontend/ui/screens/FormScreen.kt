package com.example.appdevhackathon2025frontend.ui.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appdevhackathon2025frontend.ui.components.UploadImageCard

@Composable
fun FormScreen(
    navController: NavHostController,
//    formViewModel: FormViewModel = hiltViewModel()
) {
//    val uiState = formViewModel.uiState.collectAsState().value

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
                    .clickable {
                        // come back here!
                    }
            ) {
                val imahe = null
                AnimatedContent(
                    targetState = imahe,
                    label = "picture"
                ) { image ->
                    when (image) {
                        null -> UploadImageCard(onClick = {})
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
                onValueChange = {
//                    formViewModel.updateName(it)
                },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.email",
                onValueChange = {
//                    formViewModel.updateEmail(it)
                },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.phone",
                onValueChange = {
//                    formViewModel.updatePhone(it)
                },
                label = { Text("Phone") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.title",
                onValueChange = {
//                    formViewModel.updateTitle(it)
                },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.location",
                onValueChange = {
                    //formViewModel.updateLocation(it)
                },
                label = { Text("Location") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.description",
                onValueChange = {
//                    formViewModel.updateDescription(it)
                },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = "uiState.timeFound",
                onValueChange = {
//                    formViewModel.updateTimeFound(it)
                },
                label = { Text("Time Found") },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate("itemScreen")
//                    formViewModel.submitForm()
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}