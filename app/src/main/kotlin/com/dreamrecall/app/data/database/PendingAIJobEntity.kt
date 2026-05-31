package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.JobStatus
import com.dreamrecall.app.domain.model.PendingAIJob

@Entity(tableName = "pending_ai_jobs")
data class PendingAIJobEntity(
    @PrimaryKey val id: String,
    val dreamId: String,
    val status: String, // Store status enum as String
    val createdAtMillis: Long,
    val retryCount: Int,
    val errorMessage: String?
) {
    fun toDomain(): PendingAIJob = PendingAIJob(
        id = id,
        dreamId = dreamId,
        status = try { JobStatus.valueOf(status) } catch (e: Exception) { JobStatus.PENDING },
        createdAtMillis = createdAtMillis,
        retryCount = retryCount,
        errorMessage = errorMessage
    )

    companion object {
        fun fromDomain(job: PendingAIJob): PendingAIJobEntity = PendingAIJobEntity(
            id = job.id,
            dreamId = job.dreamId,
            status = job.status.name,
            createdAtMillis = job.createdAtMillis,
            retryCount = job.retryCount,
            errorMessage = job.errorMessage
        )
    }
}
