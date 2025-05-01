package com.example.appdevhackathon2025frontend.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 *  @param imageBitmap loading if null, otherwise the image itself.
 */
@Composable
fun ImageCard(
    imageBitmap: ImageBitmap?, modifier: Modifier = Modifier, onClick: () -> Unit = {}
) {
    Surface(
        modifier = modifier.clickable(onClick = { onClick() }),
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 2.dp,
    ) {
        AnimatedClampedAsyncImage(
            image = imageBitmap,
        )
    }
}

@Composable
private fun AnimatedClampedAsyncImage(image: ImageBitmap?, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
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
}

@Preview
@Composable
fun ImageCardPreview() {
    ImageCard(imageBitmap = null)
    ImageCard(imageBitmap = ImageBitmap(1, 1))
}
