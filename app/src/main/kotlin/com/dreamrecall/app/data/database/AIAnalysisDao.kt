package com.dreamrecall.app.data.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AIAnalysisDao {
    @Query("SELECT * FROM ai_analyses WHERE dreamId = :dreamId LIMIT 1")
    fun getAnalysisForDream(dreamId: String): Flow<AIAnalysisEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnalysis(analysis: AIAnalysisEntity)

    @Delete
    suspend fun deleteAnalysis(analysis: AIAnalysisEntity)
}
