package com.example.stepsandbites

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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


@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onNavigateToPlan = {}, viewModel = HomeViewModel())
}

@Preview(showBackground = true)
@Composable
fun WeeklyPlanScreenScreenPreview() {
    WeeklyPlanScreen(onNavigateToHome = {}, viewModel = PlanViewModel())
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(onNavigate = {}, viewModel = ProgressViewModel())
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(onNavigate = {}, viewModel = HistoryViewModel())
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onNavigate = {}, viewModel = ProfileViewModel())
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    MaterialTheme {
        TopBar()
    }
}

@Preview(showBackground = true)
@Composable
fun AppBottomNavigationPreview() {
    MaterialTheme {
        AppBottomNavigation(currentRoute = "inicio", onNavigate = {})
    }
}
