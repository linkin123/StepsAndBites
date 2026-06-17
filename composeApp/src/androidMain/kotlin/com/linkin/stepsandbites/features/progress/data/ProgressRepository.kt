package com.linkin.stepsandbites.features.progress.data

import com.google.firebase.firestore.FirebaseFirestore
import com.linkin.stepsandbites.features.progress.model.Achievement
import com.linkin.stepsandbites.features.progress.model.DailyProgress
import com.linkin.stepsandbites.features.progress.model.UserProgressData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class ProgressRepository actual constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    actual fun getProgress(userId: String): Flow<UserProgressData> = callbackFlow {
        val ref = firestore.collection("users").document(userId).collection("progress").document("data")
        val listener = ref.addSnapshotListener { snapshot, error ->
            if (error != null) { close(error); return@addSnapshotListener }
            val data = snapshot?.data
            val weeklyKeys = listOf("lunes", "martes", "miércoles", "jueves", "viernes", "sábado", "domingo")
            val weeklyNames = listOf("Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo")
            val weeklyShort = listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")

            @Suppress("UNCHECKED_CAST")
            val weeklyMap = data?.get("weeklyProgress") as? Map<String, Boolean> ?: emptyMap()

            val weeklyProgress = weeklyKeys.mapIndexed { i, key ->
                DailyProgress(
                    day = weeklyNames[i],
                    dayName = weeklyShort[i],
                    calories = 0,
                    protein = 0,
                    carbs = 0,
                    fat = 0,
                    isCompleted = weeklyMap[key] ?: false
                )
            }

            @Suppress("UNCHECKED_CAST")
            val achievementsList = (data?.get("achievements") as? List<*>)
                ?.filterIsInstance<Map<*, *>>()
                ?.mapNotNull { a ->
                    val title = a["title"] as? String ?: return@mapNotNull null
                    Achievement(
                        title = title,
                        description = a["description"] as? String ?: "",
                        icon = a["icon"] as? String ?: "🏆",
                        badge = a["badge"] as? String ?: "🏆",
                        color = (a["color"] as? Long) ?: 0xFFE8F5E9L
                    )
                } ?: emptyList()

            trySend(UserProgressData(
                currentStreak = (data?.get("currentStreak") as? Long)?.toInt() ?: 0,
                personalRecord = (data?.get("personalRecord") as? Long)?.toInt() ?: 0,
                totalMeals = (data?.get("totalMeals") as? Long)?.toInt() ?: 0,
                weeklyProgress = weeklyProgress,
                achievements = achievementsList
            ))
        }
        awaitClose { listener.remove() }
    }
}
