package com.linkin.stepsandbites.features.home.data

import com.linkin.stepsandbites.features.home.model.HomeFoodItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class HomeRepository actual constructor() {
    actual fun getMenuItems(): Flow<List<HomeFoodItem>> = flowOf(emptyList())
}
