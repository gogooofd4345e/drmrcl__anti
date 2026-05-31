package com.dreamrecall.app.data.database.dao

import androidx.room.*
import com.dreamrecall.app.data.database.entities.AIAnalysisEntity
import com.dreamrecall.app.data.database.entities.PendingAIJobEntity
import com.dreamrecall.app.data.database.entities.NotificationHistoryEntity
import com.dreamrecall.app.data.database.entities.ReminderSettingsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AIAnalysisDao {
    @Query("SELECT * FROM ai_analyses WHERE dream_id = :dreamId LIMIT 1")
    suspend fun getAnalysisForDream(dreamId: Long): AIAnalysisEntity?

    @Query("SELECT * FROM ai_analyses ORDER BY analyzed_at DESC")
    fun getAllAnalyses(): Flow<List<AIAnalysisEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnalysis(analysis: AIAnalysisEntity): Long

    @Query("DELETE FROM ai_analyses WHERE dream_id = :dreamId")
    suspend fun deleteAnalysisForDream(dreamId: Long)

    @Query("DELETE FROM ai_analyses")
    suspend fun deleteAllAnalyses()
}

@Dao
interface PendingAIJobDao {
    @Query("SELECT * FROM pending_ai_jobs WHERE status = 'PENDING' ORDER BY created_at ASC")
    suspend fun getPendingJobs(): List<PendingAIJobEntity>

    @Query("SELECT * FROM pending_ai_jobs ORDER BY created_at DESC")
    fun getAllJobs(): Flow<List<PendingAIJobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJob(job: PendingAIJobEntity): Long

    @Query("UPDATE pending_ai_jobs SET status = :status, retry_count = :retryCount WHERE id = :id")
    suspend fun updateJobStatus(id: Long, status: String, retryCount: Int)

    @Query("DELETE FROM pending_ai_jobs WHERE status = 'COMPLETED'")
    suspend fun clearCompletedJobs()

    @Query("DELETE FROM pending_ai_jobs")
    suspend fun deleteAllJobs()
}

@Dao
interface NotificationHistoryDao {
    @Query("SELECT * FROM notification_history ORDER BY sent_at DESC")
    fun getAllNotifications(): Flow<List<NotificationHistoryEntity>>

    @Query("SELECT COUNT(*) FROM notification_history WHERE was_read = 0")
    fun getUnreadCount(): Flow<Int>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationHistoryEntity)

    @Query("UPDATE notification_history SET was_read = 1 WHERE id = :id")
    suspend fun markAsRead(id: Long)

    @Query("UPDATE notification_history SET was_read = 1")
    suspend fun markAllAsRead()

    @Query("DELETE FROM notification_history WHERE sent_at < :before")
    suspend fun deleteOlderThan(before: Long)
}

@Dao
interface ReminderSettingsDao {
    @Query("SELECT * FROM reminder_settings ORDER BY type ASC")
    fun getAllReminders(): Flow<List<ReminderSettingsEntity>>

    @Query("SELECT * FROM reminder_settings WHERE type = :type LIMIT 1")
    suspend fun getReminderByType(type: String): ReminderSettingsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateReminder(reminder: ReminderSettingsEntity)

    @Query("UPDATE reminder_settings SET is_enabled = :enabled WHERE type = :type")
    suspend fun setReminderEnabled(type: String, enabled: Boolean)
}
