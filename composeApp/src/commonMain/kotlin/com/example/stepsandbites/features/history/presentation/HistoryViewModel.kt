package com.example.stepsandbites.features.history.presentation

import androidx.lifecycle.ViewModel
import com.example.stepsandbites.features.history.data.HistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class HistoryEvent {
    object Refresh : HistoryEvent()
}

class HistoryViewModel(
    private val repository: HistoryRepository = HistoryRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState())
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        _uiState.value = _uiState.value.copy(
            orders = repository.getOrders()
        )
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.Refresh -> loadOrders()
        }
    }
}
