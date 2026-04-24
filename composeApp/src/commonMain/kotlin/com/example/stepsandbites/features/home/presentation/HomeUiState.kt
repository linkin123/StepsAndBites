package com.example.stepsandbites.features.home.presentation

import com.example.stepsandbites.features.home.model.HomeFoodItem

data class HomeUiState(
    val streakDays: Int = 12,
    val dateText: String = "Lunes 6 de Abril",
    val dailyCalories: Int = 1850,
    val dailyProtein: Int = 120,
    val dailyMeals: String = "0/3",
    val breakfastItems: List<HomeFoodItem> = emptyList(),
    val lunchItems: List<HomeFoodItem> = emptyList(),
    val dinnerItems: List<HomeFoodItem> = emptyList(),
    val isLoading: Boolean = false
)
