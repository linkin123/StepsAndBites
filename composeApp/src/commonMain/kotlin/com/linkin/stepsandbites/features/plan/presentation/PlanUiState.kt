package com.linkin.stepsandbites.features.plan.presentation

import com.linkin.stepsandbites.features.plan.model.DayInfo
import com.linkin.stepsandbites.features.plan.model.Dish
import com.linkin.stepsandbites.features.plan.model.NutritionSummary

data class PlanUiState(
    val days: List<DayInfo> = emptyList(),
    val selectedDayIndex: Int = 0,
    val allDishes: List<Dish> = emptyList(),
    val selectedDishIds: Set<String> = emptySet(),
    val message: String? = null,
    val isLoading: Boolean = false
) {
    val nutritionSummary: NutritionSummary
        get() {
            val selected = allDishes.filter { it.id in selectedDishIds }
            return NutritionSummary(
                totalCalories = selected.sumOf { it.calories },
                totalProtein = selected.sumOf { it.protein },
                totalCarbs = selected.sumOf { it.carbs },
                totalFat = selected.sumOf { it.fat },
                totalPrice = selected.sumOf { it.price }
            )
        }
}
