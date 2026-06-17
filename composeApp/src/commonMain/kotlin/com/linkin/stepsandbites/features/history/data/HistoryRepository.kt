package com.linkin.stepsandbites.features.history.data

import com.linkin.stepsandbites.features.history.model.OrderHistory
import kotlinx.coroutines.flow.Flow

expect class HistoryRepository() {
    fun getOrders(userId: String): Flow<List<OrderHistory>>
}
