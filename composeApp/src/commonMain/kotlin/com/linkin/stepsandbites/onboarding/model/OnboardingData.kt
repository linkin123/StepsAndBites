package com.linkin.stepsandbites.onboarding.model

data class OnboardingData(
    val goal: UserGoal = UserGoal.MAINTAIN,
    val dailyCalories: Int = 1850,
    val dietaryPreferences: List<String> = emptyList(),
    val address: String = "",
    val notifyMealReminder: Boolean = true,
    val notifyOrderStatus: Boolean = true,
    val notifyOffers: Boolean = false
)

enum class UserGoal(val label: String, val emoji: String, val description: String, val calories: Int) {
    LOSE_FAT("Bajar grasa", "📉", "Déficit calórico", 1600),
    MAINTAIN("Mantener peso", "⚖️", "Balance calórico", 1850),
    GAIN_MUSCLE("Ganar músculo", "💪", "Superávit calórico", 2200),
    MORE_ENERGY("Más energía", "⚡", "Nutrición óptima", 2000)
}
