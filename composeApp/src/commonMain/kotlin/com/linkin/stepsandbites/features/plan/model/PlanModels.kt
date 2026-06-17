package com.linkin.stepsandbites.features.plan.model

enum class MealType(val displayName: String, val icon: String) {
    BREAKFAST("Desayuno", "☀️"),
    LUNCH("Comida", "🍽️"),
    DINNER("Cena", "🌙")
}

data class Dish(
    val id: String = "",
    val name: String = "",
    val calories: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val fat: Int = 0,
    val price: Double = 0.0,
    val mealType: MealType = MealType.LUNCH,
    val emoji: String = "🍽️",
    val imageUrl: String = ""
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
