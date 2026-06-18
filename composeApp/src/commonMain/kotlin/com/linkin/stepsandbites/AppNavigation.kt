package com.linkin.stepsandbites

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linkin.stepsandbites.features.history.presentation.HistoryScreen
import com.linkin.stepsandbites.features.history.presentation.HistoryViewModel
import com.linkin.stepsandbites.features.home.presentation.HomeScreen
import com.linkin.stepsandbites.features.home.presentation.HomeViewModel
import com.linkin.stepsandbites.features.plan.presentation.PlanViewModel
import com.linkin.stepsandbites.features.plan.presentation.WeeklyPlanScreen
import com.linkin.stepsandbites.features.profile.presentation.ProfileScreen
import com.linkin.stepsandbites.features.profile.presentation.ProfileViewModel
import com.linkin.stepsandbites.features.progress.presentation.ProgressScreen
import com.linkin.stepsandbites.features.progress.presentation.ProgressViewModel
import com.linkin.stepsandbites.auth.presentation.LoginScreen
import com.linkin.stepsandbites.onboarding.data.OnboardingPreferences
import com.linkin.stepsandbites.onboarding.presentation.OnboardingScreen
import com.linkin.stepsandbites.onboarding.presentation.OnboardingViewModel

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object WeeklyPlan : Screen("WeeklyPlan")
    object Progress : Screen("progreso")
    object History : Screen("historial")
    object Profile : Screen("perfil")
}

@Composable
fun AppNavigation(
    startDestination: String,
    onboardingPrefs: OnboardingPreferences,
    homeViewModel: HomeViewModel,
    planViewModel: PlanViewModel,
    progressViewModel: ProgressViewModel,
    historyViewModel: HistoryViewModel,
    profileViewModel: ProfileViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            val onboardingViewModel: OnboardingViewModel = viewModel {
                OnboardingViewModel(onboardingPrefs)
            }
            OnboardingScreen(
                viewModel = onboardingViewModel,
                onFinished = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

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
                onSignOut = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                },
                viewModel = profileViewModel
            )
        }
    }
}
