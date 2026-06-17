package com.linkin.stepsandbites.features.home.data

import com.google.firebase.firestore.FirebaseFirestore
import com.linkin.stepsandbites.features.home.model.HomeFoodItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class HomeRepository actual constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    actual fun getMenuItems(): Flow<List<HomeFoodItem>> = callbackFlow {
        val listener = firestore
            .collection("menuItems")
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val items = snapshot?.documents?.mapNotNull { doc ->
                    val data = doc.data ?: return@mapNotNull null

                    val isDeleted = data["isDeleted"] as? Boolean ?: false
                    if (isDeleted) return@mapNotNull null

                    val category = data["category"] as? String ?: "COMIDA"
                    val tags = (data["tags"] as? List<*>)?.filterIsInstance<String>() ?: emptyList()

                    val macros = data["macros"] as? Map<*, *>
                    val protein = (macros?.get("protein") as? Long)?.toInt() ?: 0
                    val carbs = (macros?.get("carbs") as? Long)?.toInt() ?: 0
                    val fat = (macros?.get("fat") as? Long)?.toInt() ?: 0

                    val price = when (val p = data["price"]) {
                        is Long -> p.toDouble()
                        is Double -> p
                        else -> 0.0
                    }

                    HomeFoodItem(
                        emoji = categoryToEmoji(category),
                        name = data["name"] as? String ?: "",
                        description = data["description"] as? String ?: "",
                        calories = (data["calories"] as? Long)?.toInt() ?: 0,
                        protein = protein,
                        carbs = carbs,
                        fat = fat,
                        price = price,
                        tag = tags.firstOrNull() ?: "",
                        category = category,
                        imageUrl = data["imageUrl"] as? String ?: ""
                    )
                } ?: emptyList()
                trySend(items)
            }
        awaitClose { listener.remove() }
    }

    private fun categoryToEmoji(category: String): String = when (category.uppercase()) {
        "DESAYUNO" -> "🌅"
        "COMIDA" -> "☀️"
        "CENA" -> "🌙"
        "SNACK" -> "🍎"
        else -> "🍽️"
    }
}
