package com.dreamrecall.app.domain.model

import java.util.UUID

data class SleepSession(
    val id: String = UUID.randomUUID().toString(),
    val startTimeMillis: Long,
    val endTimeMillis: Long,
    val sleepDurationMillis: Long = endTimeMillis - startTimeMillis,
    val qualityScore: Int, // 1 to 100
    val notes: String? = null
)
