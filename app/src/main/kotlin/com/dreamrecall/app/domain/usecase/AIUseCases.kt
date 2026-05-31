package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.AIAnalysis
import com.dreamrecall.app.domain.repository.AIRepository
import javax.inject.Inject

class AnalyzeDreamUseCase @Inject constructor(
    private val repository: AIRepository
) {
    suspend operator fun invoke(dreamId: Long, title: String, content: String): Result<AIAnalysis> =
        repository.analyzeDream(dreamId, title, content)
}

class GetAIInsightsUseCase @Inject constructor(
    private val repository: AIRepository
) {
    suspend operator fun invoke(dreamsSummary: String): Result<String> =
        repository.generateWeeklyInsights(dreamsSummary)
}

data class AIUseCases(
    val analyzeDream: AnalyzeDreamUseCase,
    val getInsights: GetAIInsightsUseCase
)
