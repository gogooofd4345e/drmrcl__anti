package com.dreamrecall.app.domain.model

import java.util.UUID

data class PendingAIJob(
    val id: String = UUID.randomUUID().toString(),
    val dreamId: String,
    val status: JobStatus = JobStatus.PENDING,
    val createdAtMillis: Long = System.currentTimeMillis(),
    val retryCount: Int = 0,
    val errorMessage: String? = null
)

enum class JobStatus {
    PENDING,
    RUNNING,
    COMPLETED,
    FAILED
}
