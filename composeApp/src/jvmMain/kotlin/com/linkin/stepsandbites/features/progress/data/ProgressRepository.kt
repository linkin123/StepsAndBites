package com.linkin.stepsandbites.features.progress.data

import com.linkin.stepsandbites.features.progress.model.UserProgressData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class ProgressRepository actual constructor() {
    actual fun getProgress(userId: String): Flow<UserProgressData> = flowOf(UserProgressData())
}
