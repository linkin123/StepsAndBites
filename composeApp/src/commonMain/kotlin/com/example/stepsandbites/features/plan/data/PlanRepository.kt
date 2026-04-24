package com.example.stepsandbites.features.plan.data

import com.example.stepsandbites.features.plan.model.DayInfo
import com.example.stepsandbites.features.plan.model.Dish
import com.example.stepsandbites.features.plan.model.MealType

class PlanRepository {
    fun getDays() = listOf(
        DayInfo("Lun", 7), DayInfo("Mar", 8), DayInfo("Mié", 9),
        DayInfo("Jue", 10), DayInfo("Vie", 11), DayInfo("Sáb", 12)
    )

    fun getAllDishes() = listOf(
        Dish(1, "Bowl de Açaí Proteico", 420, 25, 48, 12, 8.99, MealType.BREAKFAST, "🥣"),
        Dish(2, "Omelette de Claras con Vegetales", 280, 32, 12, 8, 7.99, MealType.BREAKFAST, "🍳"),
        Dish(3, "Pollo a la Parrilla con Quinoa", 520, 45, 42, 15, 12.99, MealType.LUNCH, "🍗"),
        Dish(4, "Bowl Buddha Vegano", 480, 18, 55, 20, 11.99, MealType.LUNCH, "🥗"),
        Dish(5, "Salmón con Vegetales Asados", 450, 38, 22, 24, 15.99, MealType.DINNER, "🐟"),
        Dish(6, "Pavo con Camote y Ensalada", 420, 40, 35, 12, 13.99, MealType.DINNER, "🦃")
    )
}
