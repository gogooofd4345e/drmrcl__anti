package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.UserPreferences

@Entity(tableName = "user_preferences")
data class UserPreferencesEntity(
    @PrimaryKey val id: String,
    val isDarkMode: Boolean,
    val preferredLanguage: String,
    val notificationsEnabled: Boolean,
    val sleepTargetHours: Float,
    val isBiometricsEnabled: Boolean
) {
    fun toDomain(): UserPreferences = UserPreferences(
        id = id,
        isDarkMode = isDarkMode,
        preferredLanguage = preferredLanguage,
        notificationsEnabled = notificationsEnabled,
        sleepTargetHours = sleepTargetHours,
        isBiometricsEnabled = isBiometricsEnabled
    )

    companion object {
        fun fromDomain(preferences: UserPreferences): UserPreferencesEntity = UserPreferencesEntity(
            id = preferences.id,
            isDarkMode = preferences.isDarkMode,
            preferredLanguage = preferences.preferredLanguage,
            notificationsEnabled = preferences.notificationsEnabled,
            sleepTargetHours = preferences.sleepTargetHours,
            isBiometricsEnabled = preferences.isBiometricsEnabled
        )
    }
}
