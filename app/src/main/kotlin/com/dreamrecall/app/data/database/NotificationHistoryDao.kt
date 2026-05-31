package com.dreamrecall.app.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificationHistoryDao {
    @Query("SELECT * FROM notification_history ORDER BY timestampMillis DESC")
    fun getAllNotifications(): Flow<List<NotificationHistoryEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationHistoryEntity)

    @Query("UPDATE notification_history SET isRead = 1 WHERE id = :id")
    suspend fun markAsRead(id: String)

    @Delete
    suspend fun deleteNotification(notification: NotificationHistoryEntity)

    @Query("DELETE FROM notification_history")
    suspend fun clearAllNotifications()
}
