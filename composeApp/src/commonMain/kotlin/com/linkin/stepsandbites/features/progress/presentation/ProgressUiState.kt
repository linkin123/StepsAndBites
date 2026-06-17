package com.linkin.stepsandbites.features.progress.presentation

import com.linkin.stepsandbites.features.progress.model.Achievement
import com.linkin.stepsandbites.features.progress.model.DailyProgress

data class ProgressUiState(
    val selectedDayIndex: Int = 0,
    val weeklyProgress: List<DailyProgress> = emptyList(),
    val achievements: List<Achievement> = emptyList(),
    val isLoading: Boolean = false
)
