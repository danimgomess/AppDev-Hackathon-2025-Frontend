package com.example.appdevhackathon2025frontend.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavWrapper() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "homeScreen"
    )
    {
        composable("homeScreen") {
            HomeScreen(
                navController = navController
            )
        }
        composable("formScreen") {
            // TODO: Remove "Test" and use an idea consistent with the model's item creation.
            FormScreen(
                navController = navController
            )
        }
        composable("itemScreen") {
            ItemScreen(navController, "")
        }
    }
}