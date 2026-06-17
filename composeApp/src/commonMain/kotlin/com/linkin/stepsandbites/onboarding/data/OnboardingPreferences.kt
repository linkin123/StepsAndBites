package com.linkin.stepsandbites.onboarding.data

import com.linkin.stepsandbites.onboarding.model.OnboardingData
import kotlinx.coroutines.flow.Flow

expect class OnboardingPreferences() {
    val isCompleted: Flow<Boolean>
    suspend fun markCompleted(data: OnboardingData)
}
