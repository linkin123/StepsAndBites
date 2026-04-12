package com.example.stepsandbites

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DailyProgress(
    val day: String,
    val dayName: String,
    val calories: Int,
    val protein: Int,
    val carbs: Int,
    val fat: Int,
    val isCompleted: Boolean,
    val imageUrl: String? = null
)

data class Achievement(
    val title: String,
    val description: String,
    val icon: String,
    val badge: String,
    val color: Long
)

class ProgressViewModel : ViewModel() {
    private val _selectedDayIndex = MutableStateFlow(0)
    val selectedDayIndex: StateFlow<Int> = _selectedDayIndex.asStateFlow()

    val weeklyProgress = listOf(
        DailyProgress("Lunes", "Lun", 1850, 120, 210, 65, true),
        DailyProgress("Martes", "Mar", 1720, 115, 190, 58, true),
        DailyProgress("Miércoles", "Mié", 1910, 125, 220, 62, true),
        DailyProgress("Jueves", "Jue", 1800, 118, 205, 60, true),
        DailyProgress("Viernes", "Vie", 1650, 105, 185, 52, true),
        DailyProgress("Sábado", "Sáb", 2100, 140, 240, 75, true),
        DailyProgress("Domingo", "Dom", 0, 0, 0, 0, false)
    )

    val achievements = listOf(
        Achievement("Racha de Fuego", "10 días consecutivos", "🔥", "🔥", 0xFFE8F5E9),
        Achievement("Primera Semana", "7 días de alimentación saludable", "✅", "✅", 0xFFE8F5E9),
        Achievement("Centenario", "100 comidas ordenadas", "🏆", "🏆", 0xFFFFF8E1)
    )

    fun selectDay(index: Int) {
        _selectedDayIndex.value = index
    }
}
