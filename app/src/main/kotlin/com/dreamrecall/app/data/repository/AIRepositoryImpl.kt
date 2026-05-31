package com.dreamrecall.app.data.repository

import com.dreamrecall.app.core.network.GeminiService
import com.dreamrecall.app.data.database.dao.AIAnalysisDao
import com.dreamrecall.app.data.database.dao.PendingAIJobDao
import com.dreamrecall.app.data.database.entities.AIAnalysisEntity
import com.dreamrecall.app.data.database.entities.PendingAIJobEntity
import com.dreamrecall.app.data.datastore.SecureStorage
import com.dreamrecall.app.domain.model.*
import com.dreamrecall.app.domain.repository.AIRepository
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
class AIRepositoryImpl @Inject constructor(
    private val geminiService: GeminiService,
    private val aiAnalysisDao: AIAnalysisDao,
    private val pendingAIJobDao: PendingAIJobDao,
    private val secureStorage: SecureStorage
) : AIRepository {

    private val json = Json { ignoreUnknownKeys = true }

    override suspend fun analyzeDream(dreamId: Long, title: String, content: String): Result<AIAnalysis> {
        val apiKey = secureStorage.getApiKey() ?: return Result.failure(Exception("No API key"))
        val result = geminiService.analyzeDream(apiKey, title, content)
        return result.map { analysisResult ->
            val entity = AIAnalysisEntity(
                dreamId = dreamId,
                summary = analysisResult.summary,
                keywords = json.encodeToString(analysisResult.keywords),
                symbols = json.encodeToString(analysisResult.symbols),
                themes = json.encodeToString(analysisResult.themes),
                sentiment = analysisResult.sentiment,
                suggestions = json.encodeToString(analysisResult.suggestions),
                analyzedAt = System.currentTimeMillis()
            )
            aiAnalysisDao.insertAnalysis(entity)
            entity.toDomain()
        }
    }

    override suspend fun getAnalysisForDream(dreamId: Long): AIAnalysis? =
        aiAnalysisDao.getAnalysisForDream(dreamId)?.toDomain()

    override suspend fun generateWeeklyInsights(dreamsSummary: String): Result<String> {
        val apiKey = secureStorage.getApiKey() ?: return Result.failure(Exception("No API key"))
        return geminiService.generateWeeklyInsights(apiKey, dreamsSummary)
    }

    override suspend fun validateApiKey(apiKey: String): Boolean =
        geminiService.validateApiKey(apiKey)

    override fun getPendingJobs(): Flow<List<PendingAIJob>> =
        pendingAIJobDao.getAllJobs().map { list -> list.map { it.toDomain() } }

    override suspend fun addPendingJob(dreamId: Long) {
        pendingAIJobDao.insertJob(
            PendingAIJobEntity(dreamId = dreamId, createdAt = System.currentTimeMillis())
        )
    }

    override suspend fun processPendingJobs() {
        val jobs = pendingAIJobDao.getPendingJobs()
        jobs.forEach { job ->
            pendingAIJobDao.updateJobStatus(job.id, "COMPLETED", job.retryCount)
        }
    }

    override suspend fun clearCompletedJobs() = pendingAIJobDao.clearCompletedJobs()

    override suspend fun deleteAllAnalyses() = aiAnalysisDao.deleteAllAnalyses()

    private fun AIAnalysisEntity.toDomain(): AIAnalysis = AIAnalysis(
        id = id,
        dreamId = dreamId,
        summary = summary,
        keywords = runCatching { json.decodeFromString<List<String>>(keywords) }.getOrDefault(emptyList()),
        symbols = runCatching { json.decodeFromString<List<String>>(symbols) }.getOrDefault(emptyList()),
        themes = runCatching { json.decodeFromString<List<String>>(themes) }.getOrDefault(emptyList()),
        sentiment = sentiment,
        suggestions = runCatching { json.decodeFromString<List<String>>(suggestions) }.getOrDefault(emptyList()),
        analyzedAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(analyzedAt), ZoneId.systemDefault()),
        rawResponse = rawResponse
    )

    private fun PendingAIJobEntity.toDomain(): PendingAIJob = PendingAIJob(
        id = id,
        dreamId = dreamId,
        createdAt = LocalDateTime.ofInstant(Instant.ofEpochMilli(createdAt), ZoneId.systemDefault()),
        status = runCatching { JobStatus.valueOf(status) }.getOrDefault(JobStatus.PENDING),
        retryCount = retryCount
    )
}
