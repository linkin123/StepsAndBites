package com.example.stepsandbites

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


sealed class Screen(val route: String) {
    object Home : Screen("home")
    object WeeklyPlan : Screen("WeeklyPlan")
}

@Composable
fun App() {
    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Color(0xFF2E7D32),
            onPrimary = Color.White,
            primaryContainer = Color(0xFFE8F5E9),
            onPrimaryContainer = Color(0xFF1B5E20),
            surface = Color.White,
        )
    ) {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    onNavigateToPlan = {
                        navController.navigate(Screen.WeeklyPlan.route)
                    }
                )
            }

            composable(Screen.WeeklyPlan.route) {
                WeeklyPlanScreen(
                    onNavigateToHome = {
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}
