package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.repository.UserPreferencesRepository
import javax.inject.Inject

class UpdateUserPreferencesUseCase @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) {
    suspend fun updateDarkMode(isDarkMode: Boolean) {
        userPreferencesRepository.updateDarkMode(isDarkMode)
    }

    suspend fun updatePreferredLanguage(languageCode: String) {
        userPreferencesRepository.updatePreferredLanguage(languageCode)
    }

    suspend fun updateNotificationsEnabled(enabled: Boolean) {
        userPreferencesRepository.updateNotificationsEnabled(enabled)
    }

    suspend fun updateSleepTargetHours(hours: Float) {
        if (hours <= 0f || hours > 24f) {
            throw IllegalArgumentException("Sleep target hours must be between 0 and 24")
        }
        userPreferencesRepository.updateSleepTargetHours(hours)
    }

    suspend fun updateBiometricsEnabled(enabled: Boolean) {
        userPreferencesRepository.updateBiometricsEnabled(enabled)
    }

    suspend fun saveSecureApiKey(apiKey: String) {
        if (apiKey.isBlank()) {
            throw IllegalArgumentException("API Key cannot be empty")
        }
        userPreferencesRepository.saveSecureToken("gemini_api_key", apiKey)
    }

    suspend fun getSecureApiKey(): String? {
        return userPreferencesRepository.getSecureToken("gemini_api_key")
    }

    suspend fun clearSecureApiKey() {
        userPreferencesRepository.deleteSecureToken("gemini_api_key")
    }
}
