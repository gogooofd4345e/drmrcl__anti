package com.dreamrecall.app.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepSessionDao {
    @Query("SELECT * FROM sleep_sessions ORDER BY startTimeMillis DESC")
    fun getAllSleepSessions(): Flow<List<SleepSessionEntity>>

    @Query("SELECT * FROM sleep_sessions WHERE id = :id")
    fun getSleepSessionById(id: String): Flow<SleepSessionEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepSession(session: SleepSessionEntity)

    @Update
    suspend fun updateSleepSession(session: SleepSessionEntity)

    @Delete
    suspend fun deleteSleepSession(session: SleepSessionEntity)
}
