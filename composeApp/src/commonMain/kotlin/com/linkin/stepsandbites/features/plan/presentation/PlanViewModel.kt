package com.linkin.stepsandbites.features.plan.presentation

import androidx.lifecycle.ViewModel
import com.linkin.stepsandbites.features.plan.data.PlanRepository
import com.linkin.stepsandbites.features.plan.model.Dish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

sealed class PlanEvent {
    data class SelectDay(val index: Int) : PlanEvent()
    data class ToggleDish(val dish: Dish) : PlanEvent()
    object ClearMessage : PlanEvent()
}

class PlanViewModel(
    private val repository: PlanRepository = PlanRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(PlanUiState())
    val uiState: StateFlow<PlanUiState> = _uiState.asStateFlow()

    init {
        loadPlanData()
    }

    private fun loadPlanData() {
        _uiState.value = _uiState.value.copy(
            days = repository.getDays(),
            allDishes = repository.getAllDishes()
        )
    }

    fun onEvent(event: PlanEvent) {
        when (event) {
            is PlanEvent.SelectDay -> {
                _uiState.update { it.copy(selectedDayIndex = event.index) }
            }
            is PlanEvent.ToggleDish -> {
                _uiState.update { state ->
                    val newSelected = if (state.selectedDishIds.contains(event.dish.id)) {
                        state.selectedDishIds - event.dish.id
                    } else {
                        state.selectedDishIds + event.dish.id
                    }
                    state.copy(
                        selectedDishIds = newSelected,
                        message = if (newSelected.size > state.selectedDishIds.size) "Agregado" else "Eliminado"
                    )
                }
            }
            PlanEvent.ClearMessage -> {
                _uiState.update { it.copy(message = null) }
            }
        }
    }
}
