package com.dreamrecall.app.data.repository

import com.dreamrecall.app.data.database.dao.DreamDao
import com.dreamrecall.app.data.database.entities.DreamEntity
import com.dreamrecall.app.domain.model.*
import com.dreamrecall.app.domain.repository.DreamRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DreamRepositoryImpl @Inject constructor(
    private val dreamDao: DreamDao
) : DreamRepository {

    override fun getAllDreams(): Flow<List<Dream>> =
        dreamDao.getAllDreams().map { list -> list.map { it.toDomain() } }

    override fun getRecentDreams(limit: Int): Flow<List<Dream>> =
        dreamDao.getRecentDreams(limit).map { list -> list.map { it.toDomain() } }

    override fun searchDreams(query: String): Flow<List<Dream>> =
        dreamDao.searchDreams(query).map { list -> list.map { it.toDomain() } }

    override fun getDreamsBetween(startMillis: Long, endMillis: Long): Flow<List<Dream>> =
        dreamDao.getDreamsBetween(startMillis, endMillis).map { list -> list.map { it.toDomain() } }

    override suspend fun getDreamById(id: Long): Dream? =
        dreamDao.getDreamById(id)?.toDomain()

    override suspend fun saveDream(dream: Dream): Long =
        dreamDao.insertDream(dream.toEntity())

    override suspend fun updateDream(dream: Dream) =
        dreamDao.updateDream(dream.toEntity())

    override suspend fun deleteDream(id: Long) = dreamDao.deleteDreamById(id)

    override suspend fun deleteAllDreams() = dreamDao.deleteAllDreams()

    override suspend fun getDreamCountSince(since: Long): Int =
        dreamDao.getDreamCountSince(since)

    override suspend fun getTotalDreamCount(): Int = dreamDao.getTotalDreamCount()

    override suspend fun getAverageDreamClarity(): Float =
        dreamDao.getAverageDreamClarity() ?: 0f

    override suspend fun getUnanalyzedDreams(): List<Dream> =
        dreamDao.getUnanalyzedDreams().map { it.toDomain() }

    // ─── Mappers ───────────────────────────────────────────────────────────

    private val json = Json { ignoreUnknownKeys = true }

    private fun DreamEntity.toDomain(): Dream = Dream(
        id = id,
        title = title,
        content = content,
        timestamp = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()),
        mood = runCatching { DreamMood.valueOf(mood) }.getOrDefault(DreamMood.NEUTRAL),
        clarity = clarity,
        tags = runCatching { json.decodeFromString<List<String>>(tags) }.getOrDefault(emptyList()),
        dreamType = runCatching { DreamType.valueOf(dreamType) }.getOrDefault(DreamType.REGULAR),
        isLucid = isLucid,
        isNightmare = isNightmare,
        isRecurring = isRecurring,
        voiceNotePath = voiceNotePath,
        aiSummary = aiSummary,
        aiAnalyzed = aiAnalyzed
    )

    private fun Dream.toEntity(): DreamEntity = DreamEntity(
        id = id,
        title = title,
        content = content,
        timestamp = timestamp.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
        mood = mood.name,
        clarity = clarity,
        tags = json.encodeToString(tags),
        dreamType = dreamType.name,
        isLucid = isLucid,
        isNightmare = isNightmare,
        isRecurring = isRecurring,
        voiceNotePath = voiceNotePath,
        aiSummary = aiSummary,
        aiAnalyzed = aiAnalyzed
    )
}
