package com.linkin.stepsandbites

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkin.stepsandbites.features.history.presentation.HistoryViewModel
import com.linkin.stepsandbites.features.home.presentation.HomeViewModel
import com.linkin.stepsandbites.features.plan.presentation.PlanViewModel
import com.linkin.stepsandbites.features.profile.presentation.ProfileViewModel
import com.linkin.stepsandbites.features.progress.presentation.ProgressViewModel
import com.linkin.stepsandbites.onboarding.data.OnboardingPreferences

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
        val onboardingPrefs = remember { OnboardingPreferences() }
        val isOnboardingCompleted by onboardingPrefs.isCompleted.collectAsState(initial = null)

        if (isOnboardingCompleted == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = Color(0xFF22A861))
            }
            return@MaterialTheme
        }

        val startDestination = if (isOnboardingCompleted == true) "home" else "onboarding"

        val homeViewModel: HomeViewModel = viewModel { HomeViewModel() }
        val planViewModel: PlanViewModel = viewModel { PlanViewModel() }
        val progressViewModel: ProgressViewModel = viewModel { ProgressViewModel() }
        val historyViewModel: HistoryViewModel = viewModel { HistoryViewModel() }
        val profileViewModel: ProfileViewModel = viewModel { ProfileViewModel() }

        AppNavigation(
            startDestination = startDestination,
            onboardingPrefs = onboardingPrefs,
            homeViewModel = homeViewModel,
            planViewModel = planViewModel,
            progressViewModel = progressViewModel,
            historyViewModel = historyViewModel,
            profileViewModel = profileViewModel
        )
    }
}
