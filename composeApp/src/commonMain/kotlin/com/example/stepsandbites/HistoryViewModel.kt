package com.example.stepsandbites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

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

class HistoryViewModel : ViewModel() {
    private val _orders = MutableStateFlow<List<OrderHistory>>(emptyList())
    val orders: StateFlow<List<OrderHistory>> = _orders.asStateFlow()

    init {
        _orders.value = listOf(
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
}
