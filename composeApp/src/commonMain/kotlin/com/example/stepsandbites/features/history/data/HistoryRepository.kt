package com.example.stepsandbites.features.history.data

import com.example.stepsandbites.features.history.model.OrderHistory
import com.example.stepsandbites.features.history.model.OrderItem

class HistoryRepository {
    fun getOrders(): List<OrderHistory> = listOf(
        OrderHistory(
            id = "#o1",
            date = "Domingo, 5 de abril",
            status = "Entregado",
            items = listOf(
                OrderItem("Bowl de Açaí Proteico", 1, 8.99, "🥣"),
                OrderItem("Pollo a la Parrilla con Quinoa", 1, 12.99, "🍗"),
                OrderItem("Salmón con Vegetales Asados", 1, 15.99, "🐟")
            ),
            time = "08:00",
            address = "Av. Reforma 123",
            total = 37.97
        ),
        OrderHistory(
            id = "#o2",
            date = "Sábado, 4 de abril",
            status = "Entregado",
            items = listOf(
                OrderItem("Omelette de Claras con Vegetales", 1, 7.99, "🍳"),
                OrderItem("Bowl Buddha Vegano", 1, 11.99, "🥗")
            ),
            time = "08:00",
            address = "Av. Reforma 123",
            total = 19.98
        )
    )
}
