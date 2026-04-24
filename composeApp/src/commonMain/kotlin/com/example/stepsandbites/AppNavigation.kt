package com.example.stepsandbites

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stepsandbites.features.home.presentation.HomeScreen
import com.example.stepsandbites.features.home.presentation.HomeViewModel
import com.example.stepsandbites.features.plan.presentation.WeeklyPlanScreen
import com.example.stepsandbites.features.plan.presentation.PlanViewModel
import com.example.stepsandbites.features.progress.presentation.ProgressScreen
import com.example.stepsandbites.features.progress.presentation.ProgressViewModel
import com.example.stepsandbites.features.history.presentation.HistoryScreen
import com.example.stepsandbites.features.history.presentation.HistoryViewModel
import com.example.stepsandbites.features.profile.presentation.ProfileScreen
import com.example.stepsandbites.features.profile.presentation.ProfileViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object WeeklyPlan : Screen("WeeklyPlan")
    object Progress : Screen("progreso")
    object History : Screen("historial")
    object Profile : Screen("perfil")
}

@Composable
fun AppNavigation(
    homeViewModel: HomeViewModel,
    planViewModel: PlanViewModel,
    progressViewModel: ProgressViewModel,
    historyViewModel: HistoryViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToPlan = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = homeViewModel
            )
        }

        composable(Screen.WeeklyPlan.route) {
            WeeklyPlanScreen(
                onNavigateToHome = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = planViewModel
            )
        }

        composable(Screen.Progress.route) {
            ProgressScreen(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = progressViewModel
            )
        }

        composable(Screen.History.route) {
            HistoryScreen(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = historyViewModel
            )
        }

        composable(Screen.Profile.route) {
            ProfileScreen(
                onNavigate = { route ->
                    navController.navigate(route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                viewModel = profileViewModel
            )
        }
    }
}
