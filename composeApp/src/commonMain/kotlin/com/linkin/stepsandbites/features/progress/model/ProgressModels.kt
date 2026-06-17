package com.linkin.stepsandbites.features.progress.model

data class DailyProgress(
    val day: String,
    val dayName: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val isCompleted: Boolean,
    val imageUrl: String? = null
)

data class Achievement(
    val title: String = "",
    val description: String = "",
    val icon: String = "🏆",
    val badge: String = "🏆",
    val color: Long = 0xFFE8F5E9
)

data class UserProgressData(
    val currentStreak: Int = 0,
    val personalRecord: Int = 0,
    val totalMeals: Int = 0,
    val weeklyProgress: List<DailyProgress> = emptyList(),
    val achievements: List<Achievement> = emptyList()
)
