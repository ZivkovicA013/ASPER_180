package com.novelic.novelictestapp.dashboard

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

// Enforces type safety
@Serializable
object Dashboard

// Bridge between navigation and composable ui
fun NavGraphBuilder.dashboardDestination() {
    // defines composable destination to your navigation graph
    composable<Dashboard> {
        DashboardScreen()
    }
}