package com.dreamrecall.app.domain.model

data class UserPreferences(
    val id: String = "default_user",
    val isDarkMode: Boolean = true,
    val preferredLanguage: String = "ar",
    val notificationsEnabled: Boolean = true,
    val sleepTargetHours: Float = 8.0f,
    val isBiometricsEnabled: Boolean = false
)
