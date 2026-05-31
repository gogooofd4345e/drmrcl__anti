package com.dreamrecall.app.navigation

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object ApiSetup : Screen("api_setup")
    data object Onboarding : Screen("onboarding")
    data object Home : Screen("home")
    data object CreateDream : Screen("create_dream?dreamId={dreamId}") {
        fun createRoute(dreamId: Long? = null) =
            if (dreamId != null) "create_dream?dreamId=$dreamId" else "create_dream?dreamId=-1"
    }
    data object DreamDetails : Screen("dream_details/{dreamId}") {
        fun createRoute(dreamId: Long) = "dream_details/$dreamId"
    }
    data object SleepMode : Screen("sleep_mode")
    data object Analytics : Screen("analytics")
    data object Calendar : Screen("calendar")
    data object Search : Screen("search")
    data object AIInsights : Screen("ai_insights")
    data object Notifications : Screen("notifications")
    data object Settings : Screen("settings")
}

// Bottom nav items
data class BottomNavItem(
    val route: String,
    val labelAr: String,
    val labelEn: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector
)
