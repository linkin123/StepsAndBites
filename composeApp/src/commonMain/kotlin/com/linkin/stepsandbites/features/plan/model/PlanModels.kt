package com.linkin.stepsandbites.features.plan.model

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

data class NutritionSummary(
    val totalCalories: Int,
    val totalProtein: Int,
    val totalCarbs: Int,
    val totalFat: Int,
    val totalPrice: Double
)
