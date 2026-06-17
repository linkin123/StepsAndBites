package com.linkin.stepsandbites.onboarding.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.linkin.stepsandbites.AppContextHolder
import com.linkin.stepsandbites.onboarding.model.OnboardingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

actual class OnboardingPreferences actual constructor() {
    private val dataStore = AppContextHolder.onboardingDataStore

    actual val isCompleted: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[booleanPreferencesKey("onboarding_completed")] ?: false
    }

    actual suspend fun markCompleted(data: OnboardingData) {
        dataStore.edit { prefs ->
            prefs[booleanPreferencesKey("onboarding_completed")] = true
            prefs[stringPreferencesKey("goal")] = data.goal.name
            prefs[intPreferencesKey("daily_calories")] = data.dailyCalories
            prefs[stringPreferencesKey("dietary_preferences")] = data.dietaryPreferences.joinToString(",")
            prefs[stringPreferencesKey("address")] = data.address
        }
    }
}
