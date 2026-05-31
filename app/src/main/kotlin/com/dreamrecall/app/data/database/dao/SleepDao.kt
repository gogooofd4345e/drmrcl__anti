package com.dreamrecall.app.data.database.dao

import androidx.room.*
import com.dreamrecall.app.data.database.entities.SleepSessionEntity
import com.dreamrecall.app.data.database.entities.WakeInterruptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepSessionDao {
    @Query("SELECT * FROM sleep_sessions ORDER BY start_time DESC")
    fun getAllSessions(): Flow<List<SleepSessionEntity>>

    @Query("SELECT * FROM sleep_sessions WHERE is_active = 1 LIMIT 1")
    suspend fun getActiveSession(): SleepSessionEntity?

    @Query("SELECT * FROM sleep_sessions WHERE id = :id")
    suspend fun getSessionById(id: Long): SleepSessionEntity?

    @Query("SELECT * FROM sleep_sessions ORDER BY start_time DESC LIMIT 1")
    suspend fun getLastSession(): SleepSessionEntity?

    @Query("SELECT AVG(duration_minutes) FROM sleep_sessions WHERE end_time IS NOT NULL")
    suspend fun getAverageSleepDuration(): Float?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSession(session: SleepSessionEntity): Long

    @Update
    suspend fun updateSession(session: SleepSessionEntity)

    @Query("UPDATE sleep_sessions SET end_time = :endTime, duration_minutes = :duration, is_active = 0, sleep_quality = :quality WHERE id = :id")
    suspend fun endSession(id: Long, endTime: Long, duration: Int, quality: String)

    @Query("DELETE FROM sleep_sessions")
    suspend fun deleteAllSessions()
}

@Dao
interface WakeInterruptionDao {
    @Query("SELECT * FROM wake_interruptions WHERE session_id = :sessionId ORDER BY timestamp ASC")
    fun getInterruptionsForSession(sessionId: Long): Flow<List<WakeInterruptionEntity>>

    @Query("SELECT COUNT(*) FROM wake_interruptions WHERE session_id = :sessionId")
    suspend fun getInterruptionCount(sessionId: Long): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterruption(interruption: WakeInterruptionEntity): Long

    @Delete
    suspend fun deleteInterruption(interruption: WakeInterruptionEntity)

    @Query("DELETE FROM wake_interruptions WHERE session_id = :sessionId")
    suspend fun deleteInterruptionsForSession(sessionId: Long)
}
