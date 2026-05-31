package com.dreamrecall.app.domain.model

import java.time.LocalDateTime

data class Dream(
    val id: Long = 0,
    val title: String,
    val content: String,
    val timestamp: LocalDateTime,
    val mood: DreamMood,
    val clarity: Int, // 1-5
    val tags: List<String> = emptyList(),
    val dreamType: DreamType,
    val isLucid: Boolean = false,
    val isNightmare: Boolean = false,
    val isRecurring: Boolean = false,
    val voiceNotePath: String? = null,
    val aiSummary: String? = null,
    val aiAnalyzed: Boolean = false
)

enum class DreamMood(val labelAr: String, val labelEn: String) {
    PEACEFUL("هادئ", "Peaceful"),
    EXCITING("مثير", "Exciting"),
    SCARY("مخيف", "Scary"),
    CONFUSING("مربك", "Confusing"),
    SAD("حزين", "Sad"),
    HAPPY("سعيد", "Happy"),
    NEUTRAL("محايد", "Neutral")
}

enum class DreamType(val labelAr: String, val labelEn: String) {
    REGULAR("عادي", "Regular"),
    LUCID("واعٍ", "Lucid"),
    NIGHTMARE("كابوس", "Nightmare"),
    PROPHETIC("نبوئي", "Prophetic"),
    RECURRING("متكرر", "Recurring"),
    ADVENTURE("مغامرة", "Adventure"),
    SPIRITUAL("روحاني", "Spiritual")
}
