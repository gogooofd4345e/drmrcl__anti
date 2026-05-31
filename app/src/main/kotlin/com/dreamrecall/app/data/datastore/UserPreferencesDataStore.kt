package com.dreamrecall.app.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private object Keys {
        val LANGUAGE = stringPreferencesKey("language")
        val NOTIFICATIONS_ENABLED = booleanPreferencesKey("notifications_enabled")
        val DREAM_REMINDER_TIME = stringPreferencesKey("dream_reminder_time")
        val SLEEP_REMINDER_TIME = stringPreferencesKey("sleep_reminder_time")
        val BIOMETRIC_ENABLED = booleanPreferencesKey("biometric_enabled")
        val PRIVACY_MODE = booleanPreferencesKey("privacy_mode")
        val AI_FREQUENCY_MODE = stringPreferencesKey("ai_frequency_mode")
        val GEMINI_MODEL = stringPreferencesKey("gemini_model")
        val API_KEY_SET = booleanPreferencesKey("api_key_set")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val language: Flow<String> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.LANGUAGE] ?: "ar" }

    val notificationsEnabled: Flow<Boolean> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.NOTIFICATIONS_ENABLED] ?: true }

    val biometricEnabled: Flow<Boolean> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.BIOMETRIC_ENABLED] ?: false }

    val privacyMode: Flow<Boolean> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.PRIVACY_MODE] ?: false }

    val apiKeySet: Flow<Boolean> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.API_KEY_SET] ?: false }

    val onboardingCompleted: Flow<Boolean> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.ONBOARDING_COMPLETED] ?: false }

    val dreamReminderTime: Flow<String> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.DREAM_REMINDER_TIME] ?: "08:00" }

    val sleepReminderTime: Flow<String> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.SLEEP_REMINDER_TIME] ?: "22:00" }

    val aiFrequencyMode: Flow<String> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.AI_FREQUENCY_MODE] ?: "auto" }

    val geminiModel: Flow<String> = context.dataStore.data
        .catch { emit(emptyPreferences()) }
        .map { it[Keys.GEMINI_MODEL] ?: "gemini-2.0-flash" }

    suspend fun setLanguage(lang: String) =
        context.dataStore.edit { it[Keys.LANGUAGE] = lang }

    suspend fun setNotificationsEnabled(enabled: Boolean) =
        context.dataStore.edit { it[Keys.NOTIFICATIONS_ENABLED] = enabled }

    suspend fun setBiometricEnabled(enabled: Boolean) =
        context.dataStore.edit { it[Keys.BIOMETRIC_ENABLED] = enabled }

    suspend fun setPrivacyMode(enabled: Boolean) =
        context.dataStore.edit { it[Keys.PRIVACY_MODE] = enabled }

    suspend fun setApiKeySet(set: Boolean) =
        context.dataStore.edit { it[Keys.API_KEY_SET] = set }

    suspend fun setOnboardingCompleted(completed: Boolean) =
        context.dataStore.edit { it[Keys.ONBOARDING_COMPLETED] = completed }

    suspend fun setDreamReminderTime(time: String) =
        context.dataStore.edit { it[Keys.DREAM_REMINDER_TIME] = time }

    suspend fun setSleepReminderTime(time: String) =
        context.dataStore.edit { it[Keys.SLEEP_REMINDER_TIME] = time }

    suspend fun setAiFrequencyMode(mode: String) =
        context.dataStore.edit { it[Keys.AI_FREQUENCY_MODE] = mode }

    suspend fun setGeminiModel(model: String) =
        context.dataStore.edit { it[Keys.GEMINI_MODEL] = model }

    suspend fun clearAll() = context.dataStore.edit { it.clear() }
}
