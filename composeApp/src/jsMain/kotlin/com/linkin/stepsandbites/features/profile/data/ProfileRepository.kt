package com.linkin.stepsandbites.features.profile.data

import com.linkin.stepsandbites.features.profile.presentation.ProfileUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class ProfileRepository actual constructor() {
    actual fun getProfile(userId: String): Flow<ProfileUiState> = flowOf(ProfileUiState())
    actual suspend fun saveProfile(userId: String, uiState: ProfileUiState) {}
}
