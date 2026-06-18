package com.linkin.stepsandbites.features.profile.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkin.stepsandbites.CurrentUser
import com.linkin.stepsandbites.auth.signOutUser
import com.linkin.stepsandbites.features.profile.data.ProfileRepository
import com.linkin.stepsandbites.features.profile.model.UserGoal
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ProfileEvent {
    data class UpdateGoal(val goal: UserGoal) : ProfileEvent()
    data class UpdateCalories(val calories: String) : ProfileEvent()
    data class TogglePreference(val preference: String) : ProfileEvent()
    data class UpdateMealReminders(val enabled: Boolean) : ProfileEvent()
    data class UpdateOffers(val enabled: Boolean) : ProfileEvent()
    data class UpdateTips(val enabled: Boolean) : ProfileEvent()
    object Save : ProfileEvent()
    object SignOut : ProfileEvent()
}

class ProfileViewModel(
    private val repository: ProfileRepository = ProfileRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProfileUiState(isLoading = true))
    val uiState: StateFlow<ProfileUiState> = _uiState.asStateFlow()

    private val _signOutEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val signOutEvent: SharedFlow<Unit> = _signOutEvent.asSharedFlow()

    init {
        viewModelScope.launch {
            repository.getProfile(CurrentUser.userId)
                .catch { _uiState.update { it.copy(isLoading = false) } }
                .collect { profile -> _uiState.value = profile.copy(isLoading = false) }
        }
    }

    fun onEvent(event: ProfileEvent) {
        when (event) {
            is ProfileEvent.UpdateGoal -> _uiState.update { it.copy(goal = event.goal) }
            is ProfileEvent.UpdateCalories -> _uiState.update { it.copy(dailyCalories = event.calories) }
            is ProfileEvent.TogglePreference -> {
                _uiState.update { state ->
                    val prefs = if (state.preferences.contains(event.preference))
                        state.preferences - event.preference
                    else
                        state.preferences + event.preference
                    state.copy(preferences = prefs)
                }
            }
            is ProfileEvent.UpdateMealReminders -> _uiState.update { it.copy(mealReminders = event.enabled) }
            is ProfileEvent.UpdateOffers -> _uiState.update { it.copy(offersAndPromos = event.enabled) }
            is ProfileEvent.UpdateTips -> _uiState.update { it.copy(nutritionalTips = event.enabled) }
            ProfileEvent.Save -> {
                viewModelScope.launch {
                    repository.saveProfile(CurrentUser.userId, _uiState.value)
                }
            }
            ProfileEvent.SignOut -> {
                signOutUser()
                _signOutEvent.tryEmit(Unit)
            }
        }
    }
}
