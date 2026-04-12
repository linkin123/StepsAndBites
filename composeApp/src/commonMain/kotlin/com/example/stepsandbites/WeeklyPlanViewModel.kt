package com.example.stepsandbites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class MealType(val displayName: String, val icon: String) {
    BREAKFAST("Desayuno", "☀️"),
    LUNCH("Comida", "🍽️"),
    DINNER("Cena", "🌙")
}

data class Dish(
    val id: Int,
    val name: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val price: Double,
    val mealType: MealType,
    val emoji: String
)

data class DayInfo(
    val name: String,
    val number: Int
)

class WeeklyPlanViewModel : ViewModel() {
    private val _selectedDishes = MutableStateFlow<Set<Int>>(emptySet())
    val selectedDishes: StateFlow<Set<Int>> = _selectedDishes.asStateFlow()

    private val _selectedDay = MutableStateFlow(0)
    val selectedDay: StateFlow<Int> = _selectedDay.asStateFlow()

    private val _message = MutableStateFlow<String?>(null)
    val message: StateFlow<String?> = _message.asStateFlow()

    val days = listOf(
        DayInfo("Lun", 7),
        DayInfo("Mar", 8),
        DayInfo("Mié", 9),
        DayInfo("Jue", 10),
        DayInfo("Vie", 11),
        DayInfo("Sáb", 12)
    )

    val allDishes = listOf(
        Dish(1, "Bowl de Açaí Proteico", 420, 25, 48, 12, 8.99, MealType.BREAKFAST, "🥣"),
        Dish(2, "Omelette de Claras con Vegetales", 280, 32, 12, 8, 7.99, MealType.BREAKFAST, "🍳"),
        Dish(3, "Pollo a la Parrilla con Quinoa", 520, 45, 42, 15, 12.99, MealType.LUNCH, "🍗"),
        Dish(4, "Bowl Buddha Vegano", 480, 18, 55, 20, 11.99, MealType.LUNCH, "🥗"),
        Dish(5, "Salmón con Vegetales Asados", 450, 38, 22, 24, 15.99, MealType.DINNER, "🐟"),
        Dish(6, "Pavo con Camote y Ensalada", 420, 40, 35, 12, 13.99, MealType.DINNER, "🦃")
    )

    fun selectDay(index: Int) {
        _selectedDay.value = index
    }

    fun toggleDish(dish: Dish) {
        if (_selectedDishes.value.contains(dish.id)) {
            _selectedDishes.update { it - dish.id }
            _message.value = "Platillo eliminado del plan"
        } else {
            _selectedDishes.update { it + dish.id }
            _message.value = "Platillo agregado al plan"
        }
    }

    fun clearMessage() {
        _message.value = null
    }

    fun getNutritionSummary(): NutritionSummary {
        val selected = allDishes.filter { _selectedDishes.value.contains(it.id) }
        return NutritionSummary(
            totalCalories = selected.sumOf { it.calories },
            totalProtein = selected.sumOf { it.protein },
            totalCarbs = selected.sumOf { it.carbs },
            totalFat = selected.sumOf { it.fat },
            totalPrice = selected.sumOf { it.price }
        )
    }
}

data class NutritionSummary(
    val totalCalories: Int,
    val totalProtein: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val totalPrice: Double
)
