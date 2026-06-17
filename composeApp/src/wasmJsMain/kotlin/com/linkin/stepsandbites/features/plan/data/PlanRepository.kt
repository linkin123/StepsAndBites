package com.linkin.stepsandbites.features.plan.data

import com.linkin.stepsandbites.features.plan.model.DayInfo
import com.linkin.stepsandbites.features.plan.model.Dish
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class PlanRepository actual constructor() {
    actual fun getDays(): List<DayInfo> = emptyList()
    actual fun getAllDishes(): Flow<List<Dish>> = flowOf(emptyList())
}
