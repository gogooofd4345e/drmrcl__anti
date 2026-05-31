package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.NotificationHistory

@Entity(tableName = "notification_history")
data class NotificationHistoryEntity(
    @PrimaryKey val id: String,
    val title: String,
    val body: String,
    val timestampMillis: Long,
    val type: String,
    val isRead: Boolean
) {
    fun toDomain(): NotificationHistory = NotificationHistory(
        id = id,
        title = title,
        body = body,
        timestampMillis = timestampMillis,
        type = type,
        isRead = isRead
    )

    companion object {
        fun fromDomain(notification: NotificationHistory): NotificationHistoryEntity = NotificationHistoryEntity(
            id = notification.id,
            title = notification.title,
            body = notification.body,
            timestampMillis = notification.timestampMillis,
            type = notification.type,
            isRead = notification.isRead
        )
    }
}
