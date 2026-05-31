package com.dreamrecall.app.ui.screens.dreams

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun CreateDreamScreen(navController: NavController, dreamId: Long? = null) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Create Dream Screen - ID: $dreamId")
    }
}

@Composable
fun DreamDetailsScreen(navController: NavController, dreamId: Long) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Dream Details Screen - ID: $dreamId")
    }
}
