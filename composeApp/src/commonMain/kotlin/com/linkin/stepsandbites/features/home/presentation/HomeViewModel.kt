package com.linkin.stepsandbites.features.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.linkin.stepsandbites.features.home.data.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

sealed class HomeEvent {
    object Refresh : HomeEvent()
}

class HomeViewModel(
    private val repository: HomeRepository = HomeRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState(isLoading = true))
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadHomeData()
    }

    private fun loadHomeData() {
        viewModelScope.launch {
            repository.getMenuItems()
                .catch { e -> _uiState.update { it.copy(isLoading = false, error = e.message) } }
                .collect { items ->
                    _uiState.update {
                        it.copy(
                            breakfastItems = items.filter { item -> item.category.uppercase() == "DESAYUNO" },
                            lunchItems = items.filter { item -> item.category.uppercase() == "COMIDA" },
                            dinnerItems = items.filter { item -> item.category.uppercase() == "CENA" },
                            dailyCalories = items.sumOf { item -> item.calories },
                            dailyProtein = items.sumOf { item -> item.protein },
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.Refresh -> {
                _uiState.update { it.copy(isLoading = true) }
                loadHomeData()
            }
        }
    }
}
