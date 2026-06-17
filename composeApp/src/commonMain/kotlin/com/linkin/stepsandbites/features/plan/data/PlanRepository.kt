package com.linkin.stepsandbites.features.plan.data

import com.linkin.stepsandbites.features.plan.model.DayInfo
import com.linkin.stepsandbites.features.plan.model.Dish
import kotlinx.coroutines.flow.Flow

expect class PlanRepository() {
    fun getDays(): List<DayInfo>
    fun getAllDishes(): Flow<List<Dish>>
}
