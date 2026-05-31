package com.dreamrecall.app.core.network

import kotlinx.serialization.Serializable

@Serializable
data class GeminiRequest(
    val contents: List<Content>,
    val generationConfig: GenerationConfig? = null
)

@Serializable
data class Content(
    val parts: List<Part>
)

@Serializable
data class Part(
    val text: String
)

@Serializable
data class GenerationConfig(
    val responseMimeType: String? = null,
    val responseSchema: ResponseSchema? = null
)

@Serializable
data class ResponseSchema(
    val type: String,
    val properties: Map<String, SchemaProperty>,
    val required: List<String>? = null
)

@Serializable
data class SchemaProperty(
    val type: String,
    val description: String? = null,
    val items: SchemaItem? = null
)

@Serializable
data class SchemaItem(
    val type: String
)

@Serializable
data class GeminiResponse(
    val candidates: List<Candidate>? = null
)

@Serializable
data class Candidate(
    val content: Content? = null
)

@Serializable
data class AIAnalysisResponse(
    val interpretation: String,
    val symbols: List<String> = emptyList(),
    val emotionalTone: String,
    val lucidInsights: String? = null,
    val psychologicalTags: List<String> = emptyList()
)
