package com.example.stepsandbites.features.home.model

data class HomeFoodItem(
    val emoji: String,
    val name: String,
    val description: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val price: Double,
    val tag: String
)
