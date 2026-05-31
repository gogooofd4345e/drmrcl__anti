package com.dreamrecall.app.data.repository

import com.dreamrecall.app.data.database.NotificationHistoryDao
import com.dreamrecall.app.data.database.NotificationHistoryEntity
import com.dreamrecall.app.domain.model.NotificationHistory
import com.dreamrecall.app.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val notificationHistoryDao: NotificationHistoryDao
) : NotificationRepository {

    override fun getAllNotifications(): Flow<List<NotificationHistory>> {
        return notificationHistoryDao.getAllNotifications().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertNotification(notification: NotificationHistory) {
        notificationHistoryDao.insertNotification(NotificationHistoryEntity.fromDomain(notification))
    }

    override suspend fun markAsRead(id: String) {
        notificationHistoryDao.markAsRead(id)
    }

    override suspend fun deleteNotification(notification: NotificationHistory) {
        notificationHistoryDao.deleteNotification(NotificationHistoryEntity.fromDomain(notification))
    }

    override suspend fun clearAllNotifications() {
        notificationHistoryDao.clearAllNotifications()
    }
}
