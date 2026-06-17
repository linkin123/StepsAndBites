package com.linkin.stepsandbites.features.profile.presentation

import com.linkin.stepsandbites.features.profile.model.UserGoal

data class ProfileUiState(
    val address: String = "Av. Reforma 123, CDMX",
    val goal: UserGoal = UserGoal.MAINTAIN_WEIGHT,
    val dailyCalories: String = "1850",
    val preferences: Set<String> = setOf("Sin gluten"),
    val mealReminders: Boolean = true,
    val offersAndPromos: Boolean = true,
    val nutritionalTips: Boolean = false,
    val isLoading: Boolean = false
)
