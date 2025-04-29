package com.example.appdevhackthon2025.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavWrapper() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    )
    {
        composable("home") {
            HomeScreen(
                navController = navController
            )
        }
        composable("formScreen") {
            // TODO: Remove "Test" and use an ID consistent with the model's item creation.
            FormScreen()
        }
    }
}