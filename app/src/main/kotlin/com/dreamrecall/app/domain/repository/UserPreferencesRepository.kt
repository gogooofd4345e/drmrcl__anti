package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.UserPreferences
import kotlinx.coroutines.flow.Flow

interface UserPreferencesRepository {
    fun getUserPreferences(): Flow<UserPreferences>
    suspend fun updateDarkMode(isDarkMode: Boolean)
    suspend fun updatePreferredLanguage(languageCode: String)
    suspend fun updateNotificationsEnabled(enabled: Boolean)
    suspend fun updateSleepTargetHours(hours: Float)
    suspend fun updateBiometricsEnabled(enabled: Boolean)

    // Secure storage actions
    suspend fun saveSecureToken(key: String, token: String)
    suspend fun getSecureToken(key: String): String?
    suspend fun deleteSecureToken(key: String)
}
