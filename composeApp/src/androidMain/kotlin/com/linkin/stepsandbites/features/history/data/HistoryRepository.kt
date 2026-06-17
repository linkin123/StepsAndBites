package com.linkin.stepsandbites.features.history.data

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.linkin.stepsandbites.features.history.model.OrderHistory
import com.linkin.stepsandbites.features.history.model.OrderItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.text.SimpleDateFormat
import java.util.Locale

actual class HistoryRepository actual constructor() {
    private val firestore = FirebaseFirestore.getInstance()
    private val dateFormat = SimpleDateFormat("EEEE, d 'de' MMMM", Locale("es", "MX"))

    actual fun getOrders(userId: String): Flow<List<OrderHistory>> = callbackFlow {
        val listener = firestore
            .collection("orders")
            .whereEqualTo("userId", userId)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .addSnapshotListener { snapshot, error ->
                if (error != null) { close(error); return@addSnapshotListener }
                val orders = snapshot?.documents?.mapNotNull { doc ->
                    val data = doc.data ?: return@mapNotNull null
                    val createdAt = data["createdAt"] as? com.google.firebase.Timestamp
                    val date = createdAt?.toDate()?.let { dateFormat.format(it) } ?: ""

                    @Suppress("UNCHECKED_CAST")
                    val rawItems = (data["items"] as? List<*>)?.filterIsInstance<Map<*, *>>() ?: emptyList()
                    val items = rawItems.map { item ->
                        OrderItem(
                            name = item["name"] as? String ?: "",
                            quantity = (item["quantity"] as? Long)?.toInt() ?: 1,
                            price = (item["price"] as? Double) ?: (item["price"] as? Long)?.toDouble() ?: 0.0,
                            emoji = "🍽️"
                        )
                    }

                    OrderHistory(
                        id = data["orderNumber"] as? String ?: "#${doc.id.take(6)}",
                        date = date,
                        status = data["status"] as? String ?: "Entregado",
                        items = items,
                        time = data["deliveryTime"] as? String ?: "",
                        address = data["address"] as? String ?: "",
                        total = (data["total"] as? Double) ?: (data["total"] as? Long)?.toDouble() ?: 0.0
                    )
                } ?: emptyList()
                trySend(orders)
            }
        awaitClose { listener.remove() }
    }
}
