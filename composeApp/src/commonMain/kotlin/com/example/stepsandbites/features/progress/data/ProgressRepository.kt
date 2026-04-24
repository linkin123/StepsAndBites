package com.example.stepsandbites.features.progress.data

import com.example.stepsandbites.features.progress.model.Achievement
import com.example.stepsandbites.features.progress.model.DailyProgress

class ProgressRepository {
    fun getWeeklyProgress() = listOf(
        DailyProgress("Lunes", "Lun", 1850, 120, 210, 65, true),
        DailyProgress("Martes", "Mar", 1720, 115, 190, 58, true),
        DailyProgress("Miércoles", "Mié", 1910, 125, 220, 62, true),
        DailyProgress("Jueves", "Jue", 1800, 118, 205, 60, true),
        DailyProgress("Viernes", "Vie", 1650, 105, 185, 52, true),
        DailyProgress("Sábado", "Sáb", 2100, 140, 240, 75, true),
        DailyProgress("Domingo", "Dom", 0, 0, 0, 0, false)
    )

    fun getAchievements() = listOf(
        Achievement("Racha de Fuego", "10 días consecutivos", "🔥", "🔥", 0xFFE8F5E9),
        Achievement("Primera Semana", "7 días de alimentación saludable", "✅", "✅", 0xFFE8F5E9),
        Achievement("Centenario", "100 comidas ordenadas", "🏆", "🏆", 0xFFFFF8E1)
    )
}
