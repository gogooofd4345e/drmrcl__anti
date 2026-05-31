package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.repository.NotificationRepository
import javax.inject.Inject

class MarkNotificationAsReadUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(id: String) {
        notificationRepository.markAsRead(id)
    }
}
