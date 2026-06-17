package com.linkin.stepsandbites.features.progress.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkin.stepsandbites.CurrentUser
import com.linkin.stepsandbites.features.progress.data.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class ProgressEvent {
    data class SelectDay(val index: Int) : ProgressEvent()
}

class ProgressViewModel(
    private val repository: ProgressRepository = ProgressRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProgressUiState(isLoading = true))
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    init {
        loadProgress()
    }

    private fun loadProgress() {
        viewModelScope.launch {
            repository.getProgress(CurrentUser.userId)
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { data ->
                    _uiState.update {
                        it.copy(
                            currentStreak = data.currentStreak,
                            personalRecord = data.personalRecord,
                            totalMeals = data.totalMeals,
                            weeklyProgress = data.weeklyProgress,
                            achievements = data.achievements,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun onEvent(event: ProgressEvent) {
        when (event) {
            is ProgressEvent.SelectDay -> _uiState.update { it.copy(selectedDayIndex = event.index) }
        }
    }
}
