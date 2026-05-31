package com.dreamrecall.app.data.repository

import com.dreamrecall.app.data.datastore.SecureStorage
import com.dreamrecall.app.data.datastore.UserPreferencesDataStore
import com.dreamrecall.app.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: UserPreferencesDataStore,
    private val secureStorage: SecureStorage
) : SettingsRepository {

    override fun getLanguage(): Flow<String> = dataStore.language
    override fun isNotificationsEnabled(): Flow<Boolean> = dataStore.notificationsEnabled
    override fun isBiometricEnabled(): Flow<Boolean> = dataStore.biometricEnabled
    override fun isPrivacyMode(): Flow<Boolean> = dataStore.privacyMode
    override fun isApiKeySet(): Flow<Boolean> = dataStore.apiKeySet
    override fun isOnboardingCompleted(): Flow<Boolean> = dataStore.onboardingCompleted
    override fun getDreamReminderTime(): Flow<String> = dataStore.dreamReminderTime
    override fun getSleepReminderTime(): Flow<String> = dataStore.sleepReminderTime
    override fun getAiFrequencyMode(): Flow<String> = dataStore.aiFrequencyMode

    override suspend fun setLanguage(lang: String) = dataStore.setLanguage(lang)
    override suspend fun setNotificationsEnabled(enabled: Boolean) = dataStore.setNotificationsEnabled(enabled)
    override suspend fun setBiometricEnabled(enabled: Boolean) = dataStore.setBiometricEnabled(enabled)
    override suspend fun setPrivacyMode(enabled: Boolean) = dataStore.setPrivacyMode(enabled)
    override suspend fun setOnboardingCompleted(completed: Boolean) = dataStore.setOnboardingCompleted(completed)
    override suspend fun setDreamReminderTime(time: String) = dataStore.setDreamReminderTime(time)
    override suspend fun setSleepReminderTime(time: String) = dataStore.setSleepReminderTime(time)
    override suspend fun setAiFrequencyMode(mode: String) = dataStore.setAiFrequencyMode(mode)

    override suspend fun saveApiKey(apiKey: String) {
        secureStorage.saveApiKey(apiKey)
        dataStore.setApiKeySet(true)
    }

    override fun getApiKey(): String? = secureStorage.getApiKey()

    override suspend fun clearApiKey() {
        secureStorage.clearApiKey()
        dataStore.setApiKeySet(false)
    }

    override suspend fun resetApp() {
        dataStore.clearAll()
        secureStorage.clearAll()
    }
}
