package com.dreamrecall.app.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface WakeInterruptionDao {
    @Query("SELECT * FROM wake_interruptions WHERE sleepSessionId = :sessionId ORDER BY timeMillis ASC")
    fun getInterruptionsForSession(sessionId: String): Flow<List<WakeInterruptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertInterruption(interruption: WakeInterruptionEntity)

    @Delete
    suspend fun deleteInterruption(interruption: WakeInterruptionEntity)
}
