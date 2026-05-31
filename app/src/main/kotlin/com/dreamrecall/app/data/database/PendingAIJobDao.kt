package com.dreamrecall.app.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PendingAIJobDao {
    @Query("SELECT * FROM pending_ai_jobs ORDER BY createdAtMillis ASC")
    fun getPendingJobs(): Flow<List<PendingAIJobEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPendingJob(job: PendingAIJobEntity)

    @Update
    suspend fun updatePendingJob(job: PendingAIJobEntity)

    @Delete
    suspend fun deletePendingJob(job: PendingAIJobEntity)
}
