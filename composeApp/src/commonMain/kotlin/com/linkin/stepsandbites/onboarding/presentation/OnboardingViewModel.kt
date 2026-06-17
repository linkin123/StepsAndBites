package com.linkin.stepsandbites.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkin.stepsandbites.onboarding.data.OnboardingPreferences
import com.linkin.stepsandbites.onboarding.model.OnboardingData
import com.linkin.stepsandbites.onboarding.model.UserGoal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(private val prefs: OnboardingPreferences) : ViewModel() {

    private val _data = MutableStateFlow(OnboardingData())
    val data: StateFlow<OnboardingData> = _data.asStateFlow()

    private val _currentStep = MutableStateFlow(0)
    val currentStep: StateFlow<Int> = _currentStep.asStateFlow()

    fun setGoal(goal: UserGoal) {
        _data.update { it.copy(goal = goal, dailyCalories = goal.calories) }
    }

    fun togglePreference(pref: String) {
        _data.update { current ->
            val list = current.dietaryPreferences.toMutableList()
            if (list.contains(pref)) list.remove(pref) else list.add(pref)
            current.copy(dietaryPreferences = list)
        }
    }

    fun setAddress(address: String) {
        _data.update { it.copy(address = address) }
    }

    fun toggleNotification(type: String, value: Boolean) {
        _data.update {
            when (type) {
                "meal" -> it.copy(notifyMealReminder = value)
                "order" -> it.copy(notifyOrderStatus = value)
                "offers" -> it.copy(notifyOffers = value)
                else -> it
            }
        }
    }

    fun nextStep() { _currentStep.update { (it + 1).coerceAtMost(3) } }
    fun prevStep() { _currentStep.update { (it - 1).coerceAtLeast(0) } }

    fun completeOnboarding() {
        viewModelScope.launch {
            prefs.markCompleted(_data.value)
        }
    }
}
