package com.linkin.stepsandbites.features.history.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkin.stepsandbites.CurrentUser
import com.linkin.stepsandbites.features.history.data.HistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HistoryEvent {
    object Refresh : HistoryEvent()
}

class HistoryViewModel(
    private val repository: HistoryRepository = HistoryRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HistoryUiState(isLoading = true))
    val uiState: StateFlow<HistoryUiState> = _uiState.asStateFlow()

    init {
        loadOrders()
    }

    private fun loadOrders() {
        viewModelScope.launch {
            repository.getOrders(CurrentUser.userId)
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { orders ->
                    _uiState.update { it.copy(orders = orders, isLoading = false, error = null) }
                }
        }
    }

    fun onEvent(event: HistoryEvent) {
        when (event) {
            HistoryEvent.Refresh -> {
                _uiState.update { it.copy(isLoading = true) }
                loadOrders()
            }
        }
    }
}
