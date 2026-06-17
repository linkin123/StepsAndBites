package com.linkin.stepsandbites.features.home.data

import com.linkin.stepsandbites.features.home.model.HomeFoodItem

class HomeRepository {
    fun getBreakfastItems() = listOf(
        HomeFoodItem("🥣", "Bowl de Açaí Proteico", "Bowl energético con açaí orgánico...", 420, 25, 48, 12, 8.99, "Alto en proteína"),
        HomeFoodItem("🍳", "Omelette de Claras con Vegetales", "Omelette ligero cargado de proteína...", 280, 32, 12, 8, 7.99, "Bajo en carbohidratos")
    )

    fun getLunchItems() = listOf(
        HomeFoodItem("🍗", "Pollo a la Parrilla con Quinoa", "Plato balanceado con proteína magra...", 520, 45, 42, 15, 10.99, "Equilibrado"),
        HomeFoodItem("🥗", "Bowl Buddha Vegano", "Mezcla nutritiva de garbanzos, camote...", 450, 18, 55, 20, 9.50, "Vegano")
    )

    fun getDinnerItems() = listOf(
        HomeFoodItem("🐟", "Salmón con Vegetales Asados", "Cena ligera rica en omega-3...", 450, 38, 22, 24, 15.99, "Rico en omega-3"),
        HomeFoodItem("🦃", "Pavo con Camote y Ensalada", "Cena balanceada con carbohidratos...", 420, 40, 35, 12, 13.99, "Bajo en grasa")
    )
}
