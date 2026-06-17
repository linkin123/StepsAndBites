package com.linkin.stepsandbites.features.profile.data

import com.linkin.stepsandbites.features.profile.model.UserGoal
import com.linkin.stepsandbites.features.profile.presentation.ProfileUiState

class ProfileRepository {
    fun getProfile() = ProfileUiState(
        address = "Av. Reforma 123, CDMX",
        goal = UserGoal.MAINTAIN_WEIGHT,
        dailyCalories = "1850",
        preferences = setOf("Sin gluten"),
        mealReminders = true,
        offersAndPromos = true,
        nutritionalTips = false
    )
}
