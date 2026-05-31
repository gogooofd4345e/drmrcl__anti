package com.dreamrecall.app.domain.model

import java.util.UUID

data class NotificationHistory(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val body: String,
    val timestampMillis: Long,
    val type: String, // e.g., "AI_ANALYSIS", "SLEEP_REMINDER"
    val isRead: Boolean = false
)
