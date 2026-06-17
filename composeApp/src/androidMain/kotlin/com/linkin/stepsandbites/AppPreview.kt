package com.linkin.stepsandbites

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.linkin.stepsandbites.features.home.presentation.HomeScreen
import com.linkin.stepsandbites.features.home.presentation.HomeViewModel
import com.linkin.stepsandbites.features.plan.presentation.WeeklyPlanScreen
import com.linkin.stepsandbites.features.plan.presentation.PlanViewModel
import com.linkin.stepsandbites.features.progress.presentation.ProgressScreen
import com.linkin.stepsandbites.features.progress.presentation.ProgressViewModel
import com.linkin.stepsandbites.features.history.presentation.HistoryScreen
import com.linkin.stepsandbites.features.history.presentation.HistoryViewModel
import com.linkin.stepsandbites.features.profile.presentation.ProfileScreen
import com.linkin.stepsandbites.features.profile.presentation.ProfileViewModel


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
