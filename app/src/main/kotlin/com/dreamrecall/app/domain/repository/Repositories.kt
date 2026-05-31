package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.*
import kotlinx.coroutines.flow.Flow

interface DreamRepository {
    fun getAllDreams(): Flow<List<Dream>>
    fun getRecentDreams(limit: Int = 5): Flow<List<Dream>>
    fun searchDreams(query: String): Flow<List<Dream>>
    fun getDreamsBetween(startMillis: Long, endMillis: Long): Flow<List<Dream>>
    suspend fun getDreamById(id: Long): Dream?
    suspend fun saveDream(dream: Dream): Long
    suspend fun updateDream(dream: Dream)
    suspend fun deleteDream(id: Long)
    suspend fun deleteAllDreams()
    suspend fun getDreamCountSince(since: Long): Int
    suspend fun getTotalDreamCount(): Int
    suspend fun getAverageDreamClarity(): Float
    suspend fun getUnanalyzedDreams(): List<Dream>
}

interface SleepRepository {
    fun getAllSessions(): Flow<List<SleepSession>>
    suspend fun getActiveSession(): SleepSession?
    suspend fun getLastSession(): SleepSession?
    suspend fun startSession(): Long
    suspend fun endSession(id: Long, quality: SleepQuality)
    suspend fun recordWakeInterruption(sessionId: Long, note: String): Long
    fun getInterruptionsForSession(sessionId: Long): Flow<List<WakeInterruption>>
    suspend fun getInterruptionCount(sessionId: Long): Int
    suspend fun getAverageSleepDuration(): Float
    suspend fun deleteAllSessions()
}

interface AIRepository {
    suspend fun analyzeDream(dreamId: Long, title: String, content: String): Result<AIAnalysis>
    suspend fun getAnalysisForDream(dreamId: Long): AIAnalysis?
    suspend fun generateWeeklyInsights(dreamsSummary: String): Result<String>
    suspend fun validateApiKey(apiKey: String): Boolean
    fun getPendingJobs(): Flow<List<PendingAIJob>>
    suspend fun addPendingJob(dreamId: Long)
    suspend fun processPendingJobs()
    suspend fun clearCompletedJobs()
    suspend fun deleteAllAnalyses()
}

interface SettingsRepository {
    fun getLanguage(): Flow<String>
    fun isNotificationsEnabled(): Flow<Boolean>
    fun isBiometricEnabled(): Flow<Boolean>
    fun isPrivacyMode(): Flow<Boolean>
    fun isApiKeySet(): Flow<Boolean>
    fun isOnboardingCompleted(): Flow<Boolean>
    fun getDreamReminderTime(): Flow<String>
    fun getSleepReminderTime(): Flow<String>
    fun getAiFrequencyMode(): Flow<String>
    suspend fun setLanguage(lang: String)
    suspend fun setNotificationsEnabled(enabled: Boolean)
    suspend fun setBiometricEnabled(enabled: Boolean)
    suspend fun setPrivacyMode(enabled: Boolean)
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun setDreamReminderTime(time: String)
    suspend fun setSleepReminderTime(time: String)
    suspend fun setAiFrequencyMode(mode: String)
    suspend fun saveApiKey(apiKey: String)
    fun getApiKey(): String?
    suspend fun clearApiKey()
    suspend fun resetApp()
}
