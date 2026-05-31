package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.AIAnalysis

@Entity(
    tableName = "ai_analyses",
    foreignKeys = [
        ForeignKey(
            entity = DreamEntity::class,
            parentColumns = ["id"],
            childColumns = ["dreamId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["dreamId"])]
)
data class AIAnalysisEntity(
    @PrimaryKey val id: String,
    val dreamId: String,
    val analysisDateMillis: Long,
    val interpretation: String,
    val symbols: List<String>,
    val emotionalTone: String,
    val lucidInsights: String?,
    val psychologicalTags: List<String>
) {
    fun toDomain(): AIAnalysis = AIAnalysis(
        id = id,
        dreamId = dreamId,
        analysisDateMillis = analysisDateMillis,
        interpretation = interpretation,
        symbols = symbols,
        emotionalTone = emotionalTone,
        lucidInsights = lucidInsights,
        psychologicalTags = psychologicalTags
    )

    companion object {
        fun fromDomain(analysis: AIAnalysis): AIAnalysisEntity = AIAnalysisEntity(
            id = analysis.id,
            dreamId = analysis.dreamId,
            analysisDateMillis = analysis.analysisDateMillis,
            interpretation = analysis.interpretation,
            symbols = analysis.symbols,
            emotionalTone = analysis.emotionalTone,
            lucidInsights = analysis.lucidInsights,
            psychologicalTags = analysis.psychologicalTags
        )
    }
}
