package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.NotificationHistory
import com.dreamrecall.app.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNotificationHistoryUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(): Flow<List<NotificationHistory>> {
        return notificationRepository.getAllNotifications()
    }
}
