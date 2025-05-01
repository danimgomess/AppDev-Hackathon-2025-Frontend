package com.example.appdevhackathon2025frontend.ui.screens

import android.graphics.ImageDecoder
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.appdevhackathon2025frontend.ui.components.UploadImageCard
import com.example.appdevhackathon2025frontend.viewmodel.FormViewModel

@Composable
fun FormScreen(
    navController: NavHostController, formViewModel: FormViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val pickMedia =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri == null) {
                return@rememberLauncherForActivityResult
            }
            val source = ImageDecoder.createSource(context.contentResolver, uri)
            val bitmap = ImageDecoder.decodeBitmap(source).asImageBitmap()
            formViewModel.updatePicture(bitmap)
        }
    val uiState = formViewModel.uiStateFlow.collectAsState().value
    val uiEvent = formViewModel.uiEventFlow.collectAsState().value
    LaunchedEffect(uiEvent) {
        uiEvent?.consume { payload ->
            when {
                (payload.startsWith("navigate-to-item-screen:")) -> {
                    val id = payload.removePrefix("navigate-to-item-screen:")
                    navController.navigate("itemScreen/$id")
                }

                (payload == "launch-image-picker") -> {
                    Log.d("FormScreen", "launch-image-picker")
                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                }


            }
        }
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
                    .clickable {
                        // come back here!
                    }) {
                val image = uiState.picture
                AnimatedContent(
                    targetState = image, label = "picture"
                ) { image ->
                    when (image) {
                        null -> UploadImageCard(onClick = { formViewModel.onClickUpload() })
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
                onValueChange = {
                    formViewModel.updateName(it)
                },
                label = { Text("Name") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.email,
                onValueChange = {
                    formViewModel.updateEmail(it)
                },
                label = { Text("Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.phone,
                onValueChange = {
                    formViewModel.updatePhone(it)
                },
                label = { Text("Phone") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.title,
                onValueChange = {
                    formViewModel.updateTitle(it)
                },
                label = { Text("Title") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.location,
                onValueChange = {
                    formViewModel.updateLocation(it)
                },
                label = { Text("Location") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.description,
                onValueChange = {
                    formViewModel.updateDescription(it)
                },
                label = { Text("Description") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = uiState.timeFound,
                onValueChange = {
                    formViewModel.updateTimeFound(it)
                },
                label = { Text("Time Found") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    formViewModel.onSubmitForm()
//                    val itemId = formViewModel.uiStateFlow.value.id
//                    navController.navigate("itemScreen/$itemId")
                }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                Text("Submit")
            }
        }
    }
}