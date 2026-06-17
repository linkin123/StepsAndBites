package com.linkin.stepsandbites.features.profile.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.linkin.stepsandbites.features.profile.model.UserGoal
import com.linkin.stepsandbites.features.profile.presentation.ProfileUiState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

actual class ProfileRepository actual constructor() {
    private val firestore = FirebaseFirestore.getInstance()

    actual fun getProfile(userId: String): Flow<ProfileUiState> = callbackFlow {
        val ref = firestore.collection("users").document(userId).collection("profile").document("data")
        val listener = ref.addSnapshotListener { snapshot, error ->
            if (error != null) { close(error); return@addSnapshotListener }
            val data = snapshot?.data
            if (data == null) {
                trySend(ProfileUiState())
                return@addSnapshotListener
            }
            val goalStr = data["goal"] as? String ?: "MAINTAIN_WEIGHT"
            val goal = runCatching { UserGoal.valueOf(goalStr) }.getOrDefault(UserGoal.MAINTAIN_WEIGHT)

            @Suppress("UNCHECKED_CAST")
            val prefs = (data["dietaryPreferences"] as? List<*>)?.filterIsInstance<String>()?.toSet() ?: emptySet()

            trySend(ProfileUiState(
                address = data["address"] as? String ?: "",
                goal = goal,
                dailyCalories = ((data["dailyCalorieGoal"] as? Long)?.toString()) ?: "2000",
                preferences = prefs,
                mealReminders = data["notificationsMealReminders"] as? Boolean ?: true,
                offersAndPromos = data["notificationsOffers"] as? Boolean ?: true,
                nutritionalTips = data["notificationsTips"] as? Boolean ?: false
            ))
        }
        awaitClose { listener.remove() }
    }

    actual suspend fun saveProfile(userId: String, uiState: ProfileUiState) {
        val ref = firestore.collection("users").document(userId).collection("profile").document("data")
        ref.set(
            mapOf(
                "address" to uiState.address,
                "goal" to uiState.goal.name,
                "dailyCalorieGoal" to (uiState.dailyCalories.toIntOrNull() ?: 2000),
                "dietaryPreferences" to uiState.preferences.toList(),
                "notificationsMealReminders" to uiState.mealReminders,
                "notificationsOffers" to uiState.offersAndPromos,
                "notificationsTips" to uiState.nutritionalTips
            ),
            SetOptions.merge()
        ).await()
    }
}
