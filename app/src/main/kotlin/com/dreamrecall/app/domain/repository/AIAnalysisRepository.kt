package com.dreamrecall.app.domain.repository

import com.dreamrecall.app.domain.model.AIAnalysis
import com.dreamrecall.app.domain.model.PendingAIJob
import kotlinx.coroutines.flow.Flow

interface AIAnalysisRepository {
    fun getAnalysisForDream(dreamId: String): Flow<AIAnalysis?>
    suspend fun insertAnalysis(analysis: AIAnalysis)
    suspend fun deleteAnalysis(analysis: AIAnalysis)

    // Pending Jobs
    fun getPendingJobs(): Flow<List<PendingAIJob>>
    suspend fun insertPendingJob(job: PendingAIJob)
    suspend fun updatePendingJob(job: PendingAIJob)
    suspend fun deletePendingJob(job: PendingAIJob)

    // Remote integration with Gemini
    suspend fun fetchAnalysisFromGemini(dreamTitle: String, dreamContent: String): AIAnalysis
}
