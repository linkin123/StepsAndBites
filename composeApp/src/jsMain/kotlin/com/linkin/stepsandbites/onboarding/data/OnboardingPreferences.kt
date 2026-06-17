package com.linkin.stepsandbites.onboarding.data

import com.linkin.stepsandbites.onboarding.model.OnboardingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class OnboardingPreferences actual constructor() {
    actual val isCompleted: Flow<Boolean> = flowOf(false)
    actual suspend fun markCompleted(data: OnboardingData) {}
}
