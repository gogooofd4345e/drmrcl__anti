package com.dreamrecall.app.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dreamrecall.app.domain.model.Dream

@Entity(tableName = "dreams")
data class DreamEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val dateMillis: Long,
    val sleepQuality: Int,
    val energyLevel: Int,
    val emotionalState: String,
    val tags: List<String>,
    val isLucid: Boolean,
    val isNightmare: Boolean,
    val voiceRecordingPath: String?,
    val aiAnalysisId: String?
) {
    fun toDomain(): Dream = Dream(
        id = id,
        title = title,
        content = content,
        dateMillis = dateMillis,
        sleepQuality = sleepQuality,
        energyLevel = energyLevel,
        emotionalState = emotionalState,
        tags = tags,
        isLucid = isLucid,
        isNightmare = isNightmare,
        voiceRecordingPath = voiceRecordingPath,
        aiAnalysisId = aiAnalysisId
    )

    companion object {
        fun fromDomain(dream: Dream): DreamEntity = DreamEntity(
            id = dream.id,
            title = dream.title,
            content = dream.content,
            dateMillis = dream.dateMillis,
            sleepQuality = dream.sleepQuality,
            energyLevel = dream.energyLevel,
            emotionalState = dream.emotionalState,
            tags = dream.tags,
            isLucid = dream.isLucid,
            isNightmare = dream.isNightmare,
            voiceRecordingPath = dream.voiceRecordingPath,
            aiAnalysisId = dream.aiAnalysisId
        )
    }
}
