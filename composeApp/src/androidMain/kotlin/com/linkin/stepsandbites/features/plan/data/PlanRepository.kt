package com.linkin.stepsandbites.features.plan.data

import com.google.firebase.firestore.FirebaseFirestore
import com.linkin.stepsandbites.features.plan.model.DayInfo
import com.linkin.stepsandbites.features.plan.model.Dish
import com.linkin.stepsandbites.features.plan.model.MealType
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class PlanRepository actual constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    actual fun getDays(): List<DayInfo> = listOf(
        DayInfo("Lun", 7), DayInfo("Mar", 8), DayInfo("Mié", 9),
        DayInfo("Jue", 10), DayInfo("Vie", 11), DayInfo("Sáb", 12)
    )

    actual fun getAllDishes(): Flow<List<Dish>> = callbackFlow {
        val listener = firestore
            .collection("menuItems")
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val dishes = snapshot?.documents?.mapNotNull { doc ->
                    val data = doc.data ?: return@mapNotNull null

                    val isDeleted = data["isDeleted"] as? Boolean ?: false
                    if (isDeleted) return@mapNotNull null

                    val category = data["category"] as? String ?: "COMIDA"
                    val mealType = when (category.uppercase()) {
                        "DESAYUNO" -> MealType.BREAKFAST
                        "COMIDA" -> MealType.LUNCH
                        "CENA" -> MealType.DINNER
                        else -> MealType.LUNCH
                    }

                    val macros = data["macros"] as? Map<*, *>
                    val protein = (macros?.get("protein") as? Long)?.toInt() ?: 0
                    val carbs = (macros?.get("carbs") as? Long)?.toInt() ?: 0
                    val fat = (macros?.get("fat") as? Long)?.toInt() ?: 0

                    val price = when (val p = data["price"]) {
                        is Long -> p.toDouble()
                        is Double -> p
                        else -> 0.0
                    }

                    Dish(
                        id = doc.id,
                        name = data["name"] as? String ?: "",
                        calories = (data["calories"] as? Long)?.toInt() ?: 0,
                        protein = protein,
                        carbs = carbs,
                        fat = fat,
                        price = price,
                        mealType = mealType,
                        emoji = when (category.uppercase()) {
                            "DESAYUNO" -> "🌅"
                            "COMIDA" -> "☀️"
                            "CENA" -> "🌙"
                            "SNACK" -> "🍎"
                            else -> "🍽️"
                        },
                        imageUrl = data["imageUrl"] as? String ?: ""
                    )
                } ?: emptyList()
                trySend(dishes)
            }
        awaitClose { listener.remove() }
    }
}
