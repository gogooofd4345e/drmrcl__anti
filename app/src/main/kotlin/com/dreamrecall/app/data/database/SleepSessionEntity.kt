package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.SleepSession

@Entity(tableName = "sleep_sessions")
data class SleepSessionEntity(
    @PrimaryKey val id: String,
    val startTimeMillis: Long,
    val endTimeMillis: Long,
    val sleepDurationMillis: Long,
    val qualityScore: Int,
    val notes: String?
) {
    fun toDomain(): SleepSession = SleepSession(
        id = id,
        startTimeMillis = startTimeMillis,
        endTimeMillis = endTimeMillis,
        sleepDurationMillis = sleepDurationMillis,
        qualityScore = qualityScore,
        notes = notes
    )

    companion object {
        fun fromDomain(session: SleepSession): SleepSessionEntity = SleepSessionEntity(
            id = session.id,
            startTimeMillis = session.startTimeMillis,
            endTimeMillis = session.endTimeMillis,
            sleepDurationMillis = session.sleepDurationMillis,
            qualityScore = session.qualityScore,
            notes = session.notes
        )
    }
}
