package com.dreamrecall.app.data.repository

import com.dreamrecall.app.data.database.dao.SleepSessionDao
import com.dreamrecall.app.data.database.dao.WakeInterruptionDao
import com.dreamrecall.app.data.database.entities.SleepSessionEntity
import com.dreamrecall.app.data.database.entities.WakeInterruptionEntity
import com.dreamrecall.app.domain.model.*
import com.dreamrecall.app.domain.repository.SleepRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SleepRepositoryImpl @Inject constructor(
    private val sleepSessionDao: SleepSessionDao,
    private val wakeInterruptionDao: WakeInterruptionDao
) : SleepRepository {

    override fun getAllSessions(): Flow<List<SleepSession>> =
        sleepSessionDao.getAllSessions().map { list -> list.map { it.toDomain() } }

    override suspend fun getActiveSession(): SleepSession? =
        sleepSessionDao.getActiveSession()?.toDomain()

    override suspend fun getLastSession(): SleepSession? =
        sleepSessionDao.getLastSession()?.toDomain()

    override suspend fun startSession(): Long {
        val entity = SleepSessionEntity(
            startTime = System.currentTimeMillis(),
            isActive = true
        )
        return sleepSessionDao.insertSession(entity)
    }

    override suspend fun endSession(id: Long, quality: SleepQuality) {
        val session = sleepSessionDao.getSessionById(id) ?: return
        val endTime = System.currentTimeMillis()
        val durationMinutes = ((endTime - session.startTime) / 60_000).toInt()
        sleepSessionDao.endSession(id, endTime, durationMinutes, quality.name)
    }

    override suspend fun recordWakeInterruption(sessionId: Long, note: String): Long {
        val entity = WakeInterruptionEntity(
            sessionId = sessionId,
            timestamp = System.currentTimeMillis(),
            note = note
        )
        return wakeInterruptionDao.insertInterruption(entity)
    }

    override fun getInterruptionsForSession(sessionId: Long): Flow<List<WakeInterruption>> =
        wakeInterruptionDao.getInterruptionsForSession(sessionId)
            .map { list -> list.map { it.toDomain() } }

    override suspend fun getInterruptionCount(sessionId: Long): Int =
        wakeInterruptionDao.getInterruptionCount(sessionId)

    override suspend fun getAverageSleepDuration(): Float =
        sleepSessionDao.getAverageSleepDuration() ?: 0f

    override suspend fun deleteAllSessions() = sleepSessionDao.deleteAllSessions()

    private fun SleepSessionEntity.toDomain(): SleepSession = SleepSession(
        id = id,
        startTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(startTime), ZoneId.systemDefault()),
        endTime = endTime?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(it), ZoneId.systemDefault()) },
        durationMinutes = durationMinutes,
        sleepQuality = runCatching { SleepQuality.valueOf(sleepQuality) }.getOrDefault(SleepQuality.UNKNOWN),
        notes = notes,
        isActive = isActive
    )

    private fun WakeInterruptionEntity.toDomain(): WakeInterruption = WakeInterruption(
        id = id,
        sessionId = sessionId,
        timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()),
        durationMinutes = durationMinutes,
        note = note
    )
}
