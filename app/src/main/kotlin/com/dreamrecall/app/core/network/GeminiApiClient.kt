package com.dreamrecall.app.core.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiApiClient @Inject constructor(
    private val httpClient: HttpClient,
    private val secureStorage: com.dreamrecall.app.data.datastore.SecureStorage
) {
    companion object {
        private const val BASE_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent"
        const val GEMINI_API_KEY_PREF = "gemini_api_key"
    }

    suspend fun analyzeDream(
        dreamTitle: String,
        dreamContent: String
    ): AIAnalysisResponse {
        val apiKey = secureStorage.getString(GEMINI_API_KEY_PREF)
            ?: throw IllegalStateException("Gemini API Key is not set in Secure Storage")

        val prompt = """
            You are an expert dream psychologist and interpreter.
            Analyze the following dream and output a structured interpretation.
            Make sure to be respectful and insightful.
            Title: $dreamTitle
            Content: $dreamContent
        """.trimIndent()

        val requestBody = GeminiRequest(
            contents = listOf(Content(parts = listOf(Part(text = prompt)))),
            generationConfig = GenerationConfig(
                responseMimeType = "application/json",
                responseSchema = ResponseSchema(
                    type = "OBJECT",
                    properties = mapOf(
                        "interpretation" to SchemaProperty(type = "STRING", description = "A detailed psychological and symbolic interpretation of the dream"),
                        "symbols" to SchemaProperty(type = "ARRAY", description = "Key symbols found in the dream and their meanings", items = SchemaItem(type = "STRING")),
                        "emotionalTone" to SchemaProperty(type = "STRING", description = "The dominant emotional state expressed in the dream"),
                        "lucidInsights" to SchemaProperty(type = "STRING", description = "Insights or tips on lucidity and awareness related to this dream (optional)"),
                        "psychologicalTags" to SchemaProperty(type = "ARRAY", description = "Tags representing the themes or psychological topics in the dream", items = SchemaItem(type = "STRING"))
                    ),
                    required = listOf("interpretation", "symbols", "emotionalTone", "psychologicalTags")
                )
            )
        )

        val urlWithKey = "$BASE_URL?key=$apiKey"
        val response: GeminiResponse = httpClient.post(urlWithKey) {
            contentType(ContentType.Application.Json)
            setBody(requestBody)
        }.body()

        val responseText = response.candidates?.firstOrNull()?.content?.parts?.firstOrNull()?.text
            ?: throw Exception("No response received from Gemini API")

        return Json { ignoreUnknownKeys = true }.decodeFromString(responseText)
    }
}
