package com.dreamrecall.app.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dreamrecall.app.data.database.Converters

@Entity(tableName = "dreams")
@TypeConverters(Converters::class)
data class DreamEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "timestamp") val timestamp: Long, // epoch millis
    @ColumnInfo(name = "mood") val mood: String,
    @ColumnInfo(name = "clarity") val clarity: Int,
    @ColumnInfo(name = "tags") val tags: String, // JSON array
    @ColumnInfo(name = "dream_type") val dreamType: String,
    @ColumnInfo(name = "is_lucid") val isLucid: Boolean = false,
    @ColumnInfo(name = "is_nightmare") val isNightmare: Boolean = false,
    @ColumnInfo(name = "is_recurring") val isRecurring: Boolean = false,
    @ColumnInfo(name = "voice_note_path") val voiceNotePath: String? = null,
    @ColumnInfo(name = "ai_summary") val aiSummary: String? = null,
    @ColumnInfo(name = "ai_analyzed") val aiAnalyzed: Boolean = false
)

@Entity(tableName = "sleep_sessions")
data class SleepSessionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "start_time") val startTime: Long,
    @ColumnInfo(name = "end_time") val endTime: Long? = null,
    @ColumnInfo(name = "duration_minutes") val durationMinutes: Int = 0,
    @ColumnInfo(name = "sleep_quality") val sleepQuality: String = "UNKNOWN",
    @ColumnInfo(name = "notes") val notes: String = "",
    @ColumnInfo(name = "is_active") val isActive: Boolean = true
)

@Entity(tableName = "wake_interruptions")
data class WakeInterruptionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "session_id") val sessionId: Long,
    @ColumnInfo(name = "timestamp") val timestamp: Long,
    @ColumnInfo(name = "duration_minutes") val durationMinutes: Int = 0,
    @ColumnInfo(name = "note") val note: String = ""
)

@Entity(tableName = "ai_analyses")
data class AIAnalysisEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "dream_id") val dreamId: Long,
    @ColumnInfo(name = "summary") val summary: String,
    @ColumnInfo(name = "keywords") val keywords: String, // JSON array
    @ColumnInfo(name = "symbols") val symbols: String, // JSON array
    @ColumnInfo(name = "themes") val themes: String, // JSON array
    @ColumnInfo(name = "sentiment") val sentiment: String,
    @ColumnInfo(name = "suggestions") val suggestions: String, // JSON array
    @ColumnInfo(name = "analyzed_at") val analyzedAt: Long,
    @ColumnInfo(name = "raw_response") val rawResponse: String = ""
)

@Entity(tableName = "pending_ai_jobs")
data class PendingAIJobEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "dream_id") val dreamId: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "status") val status: String = "PENDING",
    @ColumnInfo(name = "retry_count") val retryCount: Int = 0
)

@Entity(tableName = "notification_history")
data class NotificationHistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "body") val body: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "sent_at") val sentAt: Long,
    @ColumnInfo(name = "was_read") val wasRead: Boolean = false
)

@Entity(tableName = "reminder_settings")
data class ReminderSettingsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "time_hour") val timeHour: Int,
    @ColumnInfo(name = "time_minute") val timeMinute: Int,
    @ColumnInfo(name = "is_enabled") val isEnabled: Boolean = true,
    @ColumnInfo(name = "days_of_week") val daysOfWeek: String = "1,2,3,4,5,6,7"
)
