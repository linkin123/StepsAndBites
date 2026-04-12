package com.example.stepsandbites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class UserGoal {
    LOSE_FAT, MAINTAIN_WEIGHT, GAIN_MUSCLE
}

data class ProfileState(
    val address: String = "Av. Reforma 123, CDMX",
    val goal: UserGoal = UserGoal.MAINTAIN_WEIGHT,
    val dailyCalories: String = "1850",
    val preferences: Set<String> = setOf("Sin gluten"),
    val mealReminders: Boolean = true,
    val offersAndPromos: Boolean = true,
    val nutritionalTips: Boolean = false
)

class ProfileViewModel : ViewModel() {
    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _state.asStateFlow()

    fun updateGoal(goal: UserGoal) {
        _state.value = _state.value.copy(goal = goal)
    }

    fun updateCalories(calories: String) {
        _state.value = _state.value.copy(dailyCalories = calories)
    }

    fun togglePreference(preference: String) {
        val current = _state.value.preferences
        val newPrefs = if (current.contains(preference)) {
            current - preference
        } else {
            current + preference
        }
        _state.value = _state.value.copy(preferences = newPrefs)
    }

    fun updateMealReminders(enabled: Boolean) {
        _state.value = _state.value.copy(mealReminders = enabled)
    }

    fun updateOffers(enabled: Boolean) {
        _state.value = _state.value.copy(offersAndPromos = enabled)
    }

    fun updateTips(enabled: Boolean) {
        _state.value = _state.value.copy(nutritionalTips = enabled)
    }
}
