package com.example.stepsandbites.features.history.presentation

import com.example.stepsandbites.features.history.model.OrderHistory

data class HistoryUiState(
    val orders: List<OrderHistory> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
