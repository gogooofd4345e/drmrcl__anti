package com.dreamrecall.app.domain.model

import java.time.LocalDateTime

data class SleepSession(
    val id: Long = 0,
    val startTime: LocalDateTime,
    val endTime: LocalDateTime? = null,
    val durationMinutes: Int = 0,
    val wakeInterruptions: List<WakeInterruption> = emptyList(),
    val sleepQuality: SleepQuality = SleepQuality.UNKNOWN,
    val notes: String = "",
    val isActive: Boolean = true
)

data class WakeInterruption(
    val id: Long = 0,
    val sessionId: Long,
    val timestamp: LocalDateTime,
    val durationMinutes: Int = 0,
    val note: String = ""
)

enum class SleepQuality(val labelAr: String, val score: Int) {
    EXCELLENT("ممتاز", 5),
    GOOD("جيد", 4),
    FAIR("مقبول", 3),
    POOR("سيء", 2),
    TERRIBLE("سيء جداً", 1),
    UNKNOWN("غير معروف", 0)
}

data class AIAnalysis(
    val id: Long = 0,
    val dreamId: Long,
    val summary: String,
    val keywords: List<String>,
    val symbols: List<String>,
    val themes: List<String>,
    val sentiment: String,
    val suggestions: List<String>,
    val analyzedAt: LocalDateTime,
    val rawResponse: String = ""
)

data class PendingAIJob(
    val id: Long = 0,
    val dreamId: Long,
    val createdAt: LocalDateTime,
    val status: JobStatus = JobStatus.PENDING,
    val retryCount: Int = 0
)

enum class JobStatus { PENDING, IN_PROGRESS, COMPLETED, FAILED }

data class UserPreferences(
    val language: String = "ar",
    val notificationsEnabled: Boolean = true,
    val dreamReminderTime: String = "08:00",
    val sleepReminderTime: String = "22:00",
    val biometricEnabled: Boolean = false,
    val privacyMode: Boolean = false,
    val aiFrequencyMode: String = "auto",
    val geminiModel: String = "gemini-2.0-flash",
    val apiKeySet: Boolean = false,
    val onboardingCompleted: Boolean = false
)

data class SleepAnalytics(
    val totalDreams: Int = 0,
    val totalSleepSessions: Int = 0,
    val avgSleepDuration: Float = 0f,
    val avgDreamClarity: Float = 0f,
    val lucidDreamCount: Int = 0,
    val nightmareCount: Int = 0,
    val recallStreak: Int = 0,
    val topTags: List<String> = emptyList(),
    val topSymbols: List<String> = emptyList(),
    val moodDistribution: Map<String, Int> = emptyMap(),
    val dreamTypeDistribution: Map<String, Int> = emptyMap(),
    val wakeInterruptionsTotal: Int = 0,
    val avgWakeInterruptionsPerNight: Float = 0f
)
