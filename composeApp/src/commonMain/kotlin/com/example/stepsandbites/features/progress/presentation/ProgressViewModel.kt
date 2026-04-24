package com.example.stepsandbites.features.progress.presentation

import androidx.lifecycle.ViewModel
import com.example.stepsandbites.features.progress.data.ProgressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class ProgressEvent {
    data class SelectDay(val index: Int) : ProgressEvent()
}

class ProgressViewModel(
    private val repository: ProgressRepository = ProgressRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()

    init {
        loadProgressData()
    }

    private fun loadProgressData() {
        _uiState.value = _uiState.value.copy(
            weeklyProgress = repository.getWeeklyProgress(),
            achievements = repository.getAchievements()
        )
    }

    fun onEvent(event: ProgressEvent) {
        when (event) {
            is ProgressEvent.SelectDay -> {
                _uiState.update { it.copy(selectedDayIndex = event.index) }
            }
        }
    }
}
