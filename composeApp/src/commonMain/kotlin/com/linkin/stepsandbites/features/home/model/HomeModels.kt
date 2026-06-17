package com.linkin.stepsandbites.features.home.model

data class HomeFoodItem(
    val emoji: String = "🍽️",
    val name: String = "",
    val description: String = "",
    val calories: Int = 0,
    val protein: Int = 0,
    val carbs: Int = 0,
    val fat: Int = 0,
    val price: Double = 0.0,
    val tag: String = "",
    val category: String = "COMIDA",
    val imageUrl: String = ""
)
