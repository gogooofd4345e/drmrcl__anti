package com.dreamrecall.app.domain.usecase

import com.dreamrecall.app.domain.model.AIAnalysis
import com.dreamrecall.app.domain.model.Dream
import com.dreamrecall.app.domain.model.NotificationHistory
import com.dreamrecall.app.domain.repository.AIAnalysisRepository
import com.dreamrecall.app.domain.repository.DreamRepository
import com.dreamrecall.app.domain.repository.NotificationRepository
import javax.inject.Inject

class AnalyzeDreamUseCase @Inject constructor(
    private val dreamRepository: DreamRepository,
    private val aiAnalysisRepository: AIAnalysisRepository,
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(dream: Dream): Result<AIAnalysis> {
        return try {
            val analysis = aiAnalysisRepository.fetchAnalysisFromGemini(dream.title, dream.content)
            val completedAnalysis = analysis.copy(dreamId = dream.id)

            // Insert analysis
            aiAnalysisRepository.insertAnalysis(completedAnalysis)

            // Update Dream with the Analysis ID
            val updatedDream = dream.copy(aiAnalysisId = completedAnalysis.id)
            dreamRepository.updateDream(updatedDream)

            // Save a notification history record
            val notification = NotificationHistory(
                title = "تم تحليل حلمك! ✨",
                body = "انتهى الذكاء الاصطناعي من تحليل حلمك: \"${dream.title}\". اضغط للاطلاع على التفسير.",
                timestampMillis = System.currentTimeMillis(),
                type = "AI_ANALYSIS",
                isRead = false
            )
            notificationRepository.insertNotification(notification)

            Result.success(completedAnalysis)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
