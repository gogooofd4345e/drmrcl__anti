package com.dreamrecall.app.domain.model

import java.util.UUID

data class AIAnalysis(
    val id: String = UUID.randomUUID().toString(),
    val dreamId: String,
    val analysisDateMillis: Long,
    val interpretation: String,
    val symbols: List<String> = emptyList(),
    val emotionalTone: String,
    val lucidInsights: String? = null,
    val psychologicalTags: List<String> = emptyList()
)
