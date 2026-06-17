package com.linkin.stepsandbites.features.profile.data

import com.linkin.stepsandbites.features.profile.presentation.ProfileUiState
import kotlinx.coroutines.flow.Flow

expect class ProfileRepository() {
    fun getProfile(userId: String): Flow<ProfileUiState>
    suspend fun saveProfile(userId: String, uiState: ProfileUiState)
}
