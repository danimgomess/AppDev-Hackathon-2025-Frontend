package com.example.appdevhackathon2025frontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appdevhackathon2025frontend.ui.screens.NavWrapper
import com.example.appdevhackathon2025frontend.ui.theme.AppDevHackathon2025FrontendTheme

// Authors: Daniel Martins Gomes, Daniel Idakwoji.

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevHackathon2025FrontendTheme {
                NavWrapper()
            }
        }
    }
}