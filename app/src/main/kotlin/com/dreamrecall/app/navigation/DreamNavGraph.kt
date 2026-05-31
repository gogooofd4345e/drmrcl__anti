package com.dreamrecall.app.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.navigation.*
import androidx.navigation.compose.*
import com.dreamrecall.app.ui.screens.splash.SplashScreen
import com.dreamrecall.app.ui.screens.auth.ApiSetupScreen
import com.dreamrecall.app.ui.screens.auth.OnboardingScreen
import com.dreamrecall.app.ui.screens.home.HomeScreen
import com.dreamrecall.app.ui.screens.dreams.CreateDreamScreen
import com.dreamrecall.app.ui.screens.dreams.DreamDetailsScreen
import com.dreamrecall.app.ui.screens.sleep.SleepModeScreen
import com.dreamrecall.app.ui.screens.analytics.AnalyticsScreen
import com.dreamrecall.app.ui.screens.calendar.CalendarScreen
import com.dreamrecall.app.ui.screens.search.SearchScreen
import com.dreamrecall.app.ui.screens.ai.AIInsightsScreen
import com.dreamrecall.app.ui.screens.notifications.NotificationsScreen
import com.dreamrecall.app.ui.screens.settings.SettingsScreen

@Composable
fun DreamNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            fadeIn(animationSpec = androidx.compose.animation.core.tween(300)) +
                    slideInHorizontally(initialOffsetX = { it / 4 })
        },
        exitTransition = {
            fadeOut(animationSpec = androidx.compose.animation.core.tween(200)) +
                    slideOutHorizontally(targetOffsetX = { -it / 4 })
        },
        popEnterTransition = {
            fadeIn(animationSpec = androidx.compose.animation.core.tween(300)) +
                    slideInHorizontally(initialOffsetX = { -it / 4 })
        },
        popExitTransition = {
            fadeOut(animationSpec = androidx.compose.animation.core.tween(200)) +
                    slideOutHorizontally(targetOffsetX = { it / 4 })
        }
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.ApiSetup.route) {
            ApiSetupScreen(navController = navController)
        }
        composable(Screen.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(
            route = Screen.CreateDream.route,
            arguments = listOf(navArgument("dreamId") {
                type = NavType.LongType; defaultValue = -1L
            })
        ) {
            val dreamId = it.arguments?.getLong("dreamId")?.takeIf { id -> id != -1L }
            CreateDreamScreen(navController = navController, dreamId = dreamId)
        }
        composable(
            route = Screen.DreamDetails.route,
            arguments = listOf(navArgument("dreamId") { type = NavType.LongType })
        ) {
            val dreamId = it.arguments?.getLong("dreamId") ?: return@composable
            DreamDetailsScreen(navController = navController, dreamId = dreamId)
        }
        composable(Screen.SleepMode.route) {
            SleepModeScreen(navController = navController)
        }
        composable(Screen.Analytics.route) {
            AnalyticsScreen(navController = navController)
        }
        composable(Screen.Calendar.route) {
            CalendarScreen(navController = navController)
        }
        composable(Screen.Search.route) {
            SearchScreen(navController = navController)
        }
        composable(Screen.AIInsights.route) {
            AIInsightsScreen(navController = navController)
        }
        composable(Screen.Notifications.route) {
            NotificationsScreen(navController = navController)
        }
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
    }
}
