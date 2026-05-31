package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    fun isApiKeySet(): Flow<Boolean> = repository.isApiKeySet()
    fun getLanguage(): Flow<String> = repository.getLanguage()
    fun isOnboardingCompleted(): Flow<Boolean> = repository.isOnboardingCompleted()
}

class UpdateSettingsUseCase @Inject constructor(
    private val repository: SettingsRepository
) {
    suspend fun saveApiKey(apiKey: String) = repository.saveApiKey(apiKey)
    suspend fun setOnboardingCompleted(completed: Boolean) = repository.setOnboardingCompleted(completed)
    suspend fun setLanguage(lang: String) = repository.setLanguage(lang)
}

data class SettingsUseCases(
    val getSettings: GetSettingsUseCase,
    val updateSettings: UpdateSettingsUseCase
)
