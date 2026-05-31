package com.dreamrecall.app.data.repository

import com.dreamrecall.app.data.datastore.SecureStorage
import com.dreamrecall.app.data.datastore.UserPreferencesDataStore
import com.dreamrecall.app.domain.model.UserPreferences
import com.dreamrecall.app.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferencesRepositoryImpl @Inject constructor(
    private val dataStore: UserPreferencesDataStore,
    private val secureStorage: SecureStorage
) : UserPreferencesRepository {

    override fun getUserPreferences(): Flow<UserPreferences> {
        return dataStore.userPreferencesFlow
    }

    override suspend fun updateDarkMode(isDarkMode: Boolean) {
        dataStore.updateDarkMode(isDarkMode)
    }

    override suspend fun updatePreferredLanguage(languageCode: String) {
        dataStore.updatePreferredLanguage(languageCode)
    }

    override suspend fun updateNotificationsEnabled(enabled: Boolean) {
        dataStore.updateNotificationsEnabled(enabled)
    }

    override suspend fun updateSleepTargetHours(hours: Float) {
        dataStore.updateSleepTargetHours(hours)
    }

    override suspend fun updateBiometricsEnabled(enabled: Boolean) {
        dataStore.updateBiometricsEnabled(enabled)
    }

    override suspend fun saveSecureToken(key: String, token: String) {
        secureStorage.saveString(key, token)
    }

    override suspend fun getSecureToken(key: String): String? {
        return secureStorage.getString(key)
    }

    override suspend fun deleteSecureToken(key: String) {
        secureStorage.remove(key)
    }
}
