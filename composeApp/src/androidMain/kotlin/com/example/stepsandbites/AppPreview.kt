package com.example.stepsandbites

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/*
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    App()
}

 */

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen(onNavigateToPlan = {})
}

@Preview(showBackground = true)
@Composable
fun WeeklyPlanScreenScreenPreview() {
    WeeklyPlanScreen(onNavigateToHome = {})
}

@Preview(showBackground = true)
@Composable
fun ProgressScreenPreview() {
    ProgressScreen(onNavigate = {})
}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    HistoryScreen(onNavigate = {})
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(onNavigate = {})
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

