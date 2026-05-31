package com.dreamrecall.app.data.repository

import com.dreamrecall.app.core.network.GeminiApiClient
import com.dreamrecall.app.data.database.AIAnalysisDao
import com.dreamrecall.app.data.database.AIAnalysisEntity
import com.dreamrecall.app.data.database.PendingAIJobDao
import com.dreamrecall.app.data.database.PendingAIJobEntity
import com.dreamrecall.app.domain.model.AIAnalysis
import com.dreamrecall.app.domain.model.PendingAIJob
import com.dreamrecall.app.domain.repository.AIAnalysisRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AIAnalysisRepositoryImpl @Inject constructor(
    private val aiAnalysisDao: AIAnalysisDao,
    private val pendingAIJobDao: PendingAIJobDao,
    private val geminiApiClient: GeminiApiClient
) : AIAnalysisRepository {

    override fun getAnalysisForDream(dreamId: String): Flow<AIAnalysis?> {
        return aiAnalysisDao.getAnalysisForDream(dreamId).map { it?.toDomain() }
    }

    override suspend fun insertAnalysis(analysis: AIAnalysis) {
        aiAnalysisDao.insertAnalysis(AIAnalysisEntity.fromDomain(analysis))
    }

    override suspend fun deleteAnalysis(analysis: AIAnalysis) {
        aiAnalysisDao.deleteAnalysis(AIAnalysisEntity.fromDomain(analysis))
    }

    override fun getPendingJobs(): Flow<List<PendingAIJob>> {
        return pendingAIJobDao.getPendingJobs().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun insertPendingJob(job: PendingAIJob) {
        pendingAIJobDao.insertPendingJob(PendingAIJobEntity.fromDomain(job))
    }

    override suspend fun updatePendingJob(job: PendingAIJob) {
        pendingAIJobDao.updatePendingJob(PendingAIJobEntity.fromDomain(job))
    }

    override suspend fun deletePendingJob(job: PendingAIJob) {
        pendingAIJobDao.deletePendingJob(PendingAIJobEntity.fromDomain(job))
    }

    override suspend fun fetchAnalysisFromGemini(dreamTitle: String, dreamContent: String): AIAnalysis {
        val remoteResponse = geminiApiClient.analyzeDream(dreamTitle, dreamContent)
        return AIAnalysis(
            dreamId = "", // Filled by use case or caller after receiving it
            analysisDateMillis = System.currentTimeMillis(),
            interpretation = remoteResponse.interpretation,
            symbols = remoteResponse.symbols,
            emotionalTone = remoteResponse.emotionalTone,
            lucidInsights = remoteResponse.lucidInsights,
            psychologicalTags = remoteResponse.psychologicalTags
        )
    }
}
