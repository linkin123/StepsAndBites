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
    val title: String,
    val description: String,
    val icon: String,
    val badge: String,
    val color: Long
)
