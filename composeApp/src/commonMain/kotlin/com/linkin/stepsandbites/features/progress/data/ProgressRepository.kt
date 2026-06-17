package com.linkin.stepsandbites.features.progress.data

import com.linkin.stepsandbites.features.progress.model.UserProgressData
import kotlinx.coroutines.flow.Flow

expect class ProgressRepository() {
    fun getProgress(userId: String): Flow<UserProgressData>
}
