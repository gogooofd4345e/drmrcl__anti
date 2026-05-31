package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.WakeInterruption

@Entity(
    tableName = "wake_interruptions",
    foreignKeys = [
        ForeignKey(
            entity = SleepSessionEntity::class,
            parentColumns = ["id"],
            childColumns = ["sleepSessionId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["sleepSessionId"])]
)
data class WakeInterruptionEntity(
    @PrimaryKey val id: String,
    val sleepSessionId: String,
    val timeMillis: Long,
    val durationMillis: Long,
    val reason: String?,
    val notes: String?
) {
    fun toDomain(): WakeInterruption = WakeInterruption(
        id = id,
        sleepSessionId = sleepSessionId,
        timeMillis = timeMillis,
        durationMillis = durationMillis,
        reason = reason,
        notes = notes
    )

    companion object {
        fun fromDomain(interruption: WakeInterruption): WakeInterruptionEntity = WakeInterruptionEntity(
            id = interruption.id,
            sleepSessionId = interruption.sleepSessionId,
            timeMillis = interruption.timeMillis,
            durationMillis = interruption.durationMillis,
            reason = interruption.reason,
            notes = interruption.notes
        )
    }
}
