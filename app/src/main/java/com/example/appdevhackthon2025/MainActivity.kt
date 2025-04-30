package com.example.appdevhackthon2025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.appdevhackthon2025.ui.screens.NavWrapper
import com.example.appdevhackthon2025.ui.theme.AppDevHackthon2025Theme
import dagger.hilt.android.AndroidEntryPoint

// Authors: Daniel Martins Gomes, Daniel Idakwoji.

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDevHackthon2025Theme {
                NavWrapper()
            }
        }
    }
}