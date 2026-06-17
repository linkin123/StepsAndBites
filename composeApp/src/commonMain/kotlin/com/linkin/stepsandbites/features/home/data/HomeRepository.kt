package com.linkin.stepsandbites.features.home.data

import com.linkin.stepsandbites.features.home.model.HomeFoodItem
import kotlinx.coroutines.flow.Flow

expect class HomeRepository() {
    fun getMenuItems(): Flow<List<HomeFoodItem>>
}
