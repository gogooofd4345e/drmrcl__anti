package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.NotificationHistory
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getAllNotifications(): Flow<List<NotificationHistory>>
    suspend fun insertNotification(notification: NotificationHistory)
    suspend fun markAsRead(id: String)
    suspend fun deleteNotification(notification: NotificationHistory)
    suspend fun clearAllNotifications()
}
