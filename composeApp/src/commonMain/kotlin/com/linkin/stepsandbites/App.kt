package com.linkin.stepsandbites

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkin.stepsandbites.features.home.presentation.HomeViewModel
import com.linkin.stepsandbites.features.plan.presentation.PlanViewModel
import com.linkin.stepsandbites.features.progress.presentation.ProgressViewModel
import com.linkin.stepsandbites.features.history.presentation.HistoryViewModel
import com.linkin.stepsandbites.features.profile.presentation.ProfileViewModel

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
        val homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
        val planViewModel: PlanViewModel = viewModel { PlanViewModel() }
        val progressViewModel: ProgressViewModel = viewModel { ProgressViewModel() }
        val historyViewModel: HistoryViewModel = viewModel { HistoryViewModel() }
        val profileViewModel: ProfileViewModel = viewModel { ProfileViewModel() }

        AppNavigation(
            homeViewModel = homeViewModel,
            planViewModel = planViewModel,
            progressViewModel = progressViewModel,
            historyViewModel = historyViewModel,
            profileViewModel = profileViewModel
        )
    }
}
