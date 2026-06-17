package com.linkin.stepsandbites.features.progress.presentation

import com.linkin.stepsandbites.features.progress.model.Achievement
import com.linkin.stepsandbites.features.progress.model.DailyProgress

data class ProgressUiState(
    val selectedDayIndex: Int = 0,
    val currentStreak: Int = 0,
    val personalRecord: Int = 0,
    val totalMeals: Int = 0,
    val weeklyProgress: List<DailyProgress> = emptyList(),
    val achievements: List<Achievement> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
) {
    val weeklyCompletionPercent: Float
        get() = if (weeklyProgress.isEmpty()) 0f
        else weeklyProgress.count { it.isCompleted }.toFloat() / weeklyProgress.size
}
