package com.dreamrecall.app.core.network

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiApiClient @Inject constructor() {
    private val json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }

    val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(json)
        }
        install(Logging) {
            level = LogLevel.BODY
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30_000
            connectTimeoutMillis = 10_000
        }
        defaultRequest {
            contentType(ContentType.Application.Json)
        }
    }
}

@Singleton
class GeminiService @Inject constructor(
    private val apiClient: GeminiApiClient
) {
    private val baseUrl = "https://generativelanguage.googleapis.com/v1beta/models"
    private val model = "gemini-2.0-flash"

    suspend fun analyzeDream(
        apiKey: String,
        dreamTitle: String,
        dreamContent: String
    ): Result<DreamAnalysisResult> = runCatching {
        val prompt = buildDreamAnalysisPrompt(dreamTitle, dreamContent)
        val response = apiClient.httpClient.post("$baseUrl/$model:generateContent?key=$apiKey") {
            setBody(GeminiRequest(
                contents = listOf(
                    GeminiContent(
                        parts = listOf(GeminiPart(text = prompt))
                    )
                ),
                generationConfig = GenerationConfig(
                    temperature = 0.7f,
                    maxOutputTokens = 1024
                )
            ))
        }
        val body = response.bodyAsText()
        val geminiResponse = Json { ignoreUnknownKeys = true }.decodeFromString<GeminiResponse>(body)
        val text = geminiResponse.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: throw Exception("Empty response from Gemini")
        parseDreamAnalysis(text)
    }

    suspend fun validateApiKey(apiKey: String): Boolean = runCatching {
        val response = apiClient.httpClient.post("$baseUrl/$model:generateContent?key=$apiKey") {
            setBody(GeminiRequest(
                contents = listOf(
                    GeminiContent(parts = listOf(GeminiPart(text = "مرحبا")))
                )
            ))
        }
        response.status.isSuccess()
    }.getOrDefault(false)

    suspend fun generateWeeklyInsights(
        apiKey: String,
        dreamsSummary: String
    ): Result<String> = runCatching {
        val prompt = """
            أنت خبير في تحليل الأحلام وعلم النفس. بناءً على أحلام هذا الأسبوع:
            $dreamsSummary
            
            قدّم تحليلاً موجزاً باللغة العربية يشمل:
            - الأنماط المتكررة الرئيسية
            - نصيحة نفسية قصيرة
            - توقع إيجابي للأسبوع القادم
            
            اكتب بأسلوب دافئ ومطمئن في 3-4 جمل فقط.
        """.trimIndent()

        val response = apiClient.httpClient.post("$baseUrl/$model:generateContent?key=$apiKey") {
            setBody(GeminiRequest(
                contents = listOf(GeminiContent(parts = listOf(GeminiPart(text = prompt)))),
                generationConfig = GenerationConfig(temperature = 0.8f, maxOutputTokens = 512)
            ))
        }
        val body = response.bodyAsText()
        val geminiResponse = Json { ignoreUnknownKeys = true }.decodeFromString<GeminiResponse>(body)
        geminiResponse.candidates.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: throw Exception("Empty response")
    }

    private fun buildDreamAnalysisPrompt(title: String, content: String): String = """
        أنت محلل أحلام خبير. حلّل الحلم التالي وأجب بصيغة JSON فقط بدون أي نص إضافي:
        
        العنوان: $title
        المحتوى: $content
        
        أجب بالصيغة التالية بالضبط:
        {
          "summary": "ملخص موجز للحلم في جملة واحدة",
          "keywords": ["كلمة1", "كلمة2", "كلمة3"],
          "symbols": ["رمز1", "رمز2"],
          "themes": ["موضوع1", "موضوع2"],
          "sentiment": "إيجابي أو سلبي أو محايد",
          "suggestions": ["اقتراح1", "اقتراح2"]
        }
    """.trimIndent()

    private fun parseDreamAnalysis(text: String): DreamAnalysisResult {
        val cleanText = text.trim()
            .removePrefix("```json")
            .removePrefix("```")
            .removeSuffix("```")
            .trim()
        return try {
            Json { ignoreUnknownKeys = true }.decodeFromString<DreamAnalysisResult>(cleanText)
        } catch (e: Exception) {
            DreamAnalysisResult(
                summary = cleanText.take(200),
                keywords = emptyList(),
                symbols = emptyList(),
                themes = emptyList(),
                sentiment = "محايد",
                suggestions = emptyList()
            )
        }
    }
}

// ─── Gemini API Request / Response Models ───────────────────────────────────

@Serializable
data class GeminiRequest(
    val contents: List<GeminiContent>,
    val generationConfig: GenerationConfig? = null
)

@Serializable
data class GeminiContent(
    val parts: List<GeminiPart>,
    val role: String = "user"
)

@Serializable
data class GeminiPart(val text: String)

@Serializable
data class GenerationConfig(
    val temperature: Float = 0.7f,
    val maxOutputTokens: Int = 1024,
    val topP: Float = 0.95f
)

@Serializable
data class GeminiResponse(
    val candidates: List<GeminiCandidate> = emptyList()
)

@Serializable
data class GeminiCandidate(
    val content: GeminiContent
)

@Serializable
data class DreamAnalysisResult(
    val summary: String = "",
    val keywords: List<String> = emptyList(),
    val symbols: List<String> = emptyList(),
    val themes: List<String> = emptyList(),
    val sentiment: String = "محايد",
    val suggestions: List<String> = emptyList()
)
