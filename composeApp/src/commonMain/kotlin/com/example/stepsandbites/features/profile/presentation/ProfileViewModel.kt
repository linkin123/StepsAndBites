package com.example.stepsandbites.features.profile.presentation

import androidx.lifecycle.ViewModel
import com.example.stepsandbites.features.profile.data.ProfileRepository
import com.example.stepsandbites.features.profile.model.UserGoal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class ProfileEvent {
    data class UpdateGoal(val goal: UserGoal) : ProfileEvent()
    data class UpdateCalories(val calories: String) : ProfileEvent()
    data class TogglePreference(val preference: String) : ProfileEvent()
    data class UpdateMealReminders(val enabled: Boolean) : ProfileEvent()
    data class UpdateOffers(val enabled: Boolean) : ProfileEvent()
    data class UpdateTips(val enabled: Boolean) : ProfileEvent()
}

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(repository.getProfile())
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateGoal -> {
                _uiState.update { it.copy(goal = event.goal) }
            }
            is ProfileEvent.UpdateCalories -> {
                _uiState.update { it.copy(dailyCalories = event.calories) }
            }
            is ProfileEvent.TogglePreference -> {
                _uiState.update { state ->
                    val current = state.preferences
                    val newPrefs = if (current.contains(event.preference)) {
                        current - event.preference
                    } else {
                        current + event.preference
                    }
                    state.copy(preferences = newPrefs)
                }
            }
            is ProfileEvent.UpdateMealReminders -> {
                _uiState.update { it.copy(mealReminders = event.enabled) }
            }
            is ProfileEvent.UpdateOffers -> {
                _uiState.update { it.copy(offersAndPromos = event.enabled) }
            }
            is ProfileEvent.UpdateTips -> {
                _uiState.update { it.copy(nutritionalTips = event.enabled) }
            }
        }
    }
}
