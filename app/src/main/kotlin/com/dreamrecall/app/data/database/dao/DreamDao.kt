package com.dreamrecall.app.data.database.dao

import androidx.room.*
import com.dreamrecall.app.data.database.entities.DreamEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DreamDao {
    @Query("SELECT * FROM dreams ORDER BY timestamp DESC")
    fun getAllDreams(): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreams WHERE id = :id")
    suspend fun getDreamById(id: Long): DreamEntity?

    @Query("SELECT * FROM dreams ORDER BY timestamp DESC LIMIT :limit")
    fun getRecentDreams(limit: Int = 5): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreams WHERE timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    fun getDreamsBetween(start: Long, end: Long): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreams WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY timestamp DESC")
    fun searchDreams(query: String): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreams WHERE dream_type = :type ORDER BY timestamp DESC")
    fun getDreamsByType(type: String): Flow<List<DreamEntity>>

    @Query("SELECT * FROM dreams WHERE ai_analyzed = 0 ORDER BY timestamp DESC")
    suspend fun getUnanalyzedDreams(): List<DreamEntity>

    @Query("SELECT COUNT(*) FROM dreams WHERE timestamp >= :since")
    suspend fun getDreamCountSince(since: Long): Int

    @Query("SELECT COUNT(*) FROM dreams")
    suspend fun getTotalDreamCount(): Int

    @Query("SELECT AVG(clarity) FROM dreams")
    suspend fun getAverageDreamClarity(): Float?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDream(dream: DreamEntity): Long

    @Update
    suspend fun updateDream(dream: DreamEntity)

    @Delete
    suspend fun deleteDream(dream: DreamEntity)

    @Query("DELETE FROM dreams WHERE id = :id")
    suspend fun deleteDreamById(id: Long)

    @Query("DELETE FROM dreams")
    suspend fun deleteAllDreams()
}
