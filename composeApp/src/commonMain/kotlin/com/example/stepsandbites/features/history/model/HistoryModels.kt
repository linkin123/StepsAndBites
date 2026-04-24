package com.example.stepsandbites.features.history.model

data class OrderItem(
    val name: String,
    val quantity: Int,
    val price: Double,
    val emoji: String
)

data class OrderHistory(
    val id: String,
    val date: String,
    val status: String,
    val items: List<OrderItem>,
    val time: String,
    val address: String,
    val total: Double
)
