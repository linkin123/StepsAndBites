package com.linkin.stepsandbites.features.history.data

import com.linkin.stepsandbites.features.history.model.OrderHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

actual class HistoryRepository actual constructor() {
    actual fun getOrders(userId: String): Flow<List<OrderHistory>> = flowOf(emptyList())
}
