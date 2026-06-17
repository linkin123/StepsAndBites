package com.linkin.stepsandbites.features.history.presentation

import com.linkin.stepsandbites.features.history.model.OrderHistory

data class HistoryUiState(
    val orders: List<OrderHistory> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
