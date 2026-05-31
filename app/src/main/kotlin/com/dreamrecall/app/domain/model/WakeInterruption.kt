package com.dreamrecall.app.domain.model

import java.util.UUID

data class WakeInterruption(
    val id: String = UUID.randomUUID().toString(),
    val sleepSessionId: String,
    val timeMillis: Long,
    val durationMillis: Long,
    val reason: String? = null,
    val notes: String? = null
)
