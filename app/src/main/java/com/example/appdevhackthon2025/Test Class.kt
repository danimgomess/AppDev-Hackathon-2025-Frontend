package com.example.appdevhackthon2025

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun WelcomeMessage(){
    Surface {
        Column{
            Text(text = "Welcome to our project!")
        }

    }
}

@Composable
@Preview
fun TestPreview(){
    WelcomeMessage()
}
