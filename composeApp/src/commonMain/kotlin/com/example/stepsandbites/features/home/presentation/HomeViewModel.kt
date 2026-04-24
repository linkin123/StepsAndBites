package com.example.stepsandbites.features.home.presentation

import androidx.lifecycle.ViewModel
import com.example.stepsandbites.features.home.data.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

sealed class HomeEvent {
    object Refresh : HomeEvent()
}

class HomeViewModel(
    private val repository: HomeRepository = HomeRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        _uiState.value = _uiState.value.copy(
            breakfastItems = repository.getBreakfastItems(),
            lunchItems = repository.getLunchItems(),
            dinnerItems = repository.getDinnerItems()
        )
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Refresh -> loadHomeData()
        }
    }
}
