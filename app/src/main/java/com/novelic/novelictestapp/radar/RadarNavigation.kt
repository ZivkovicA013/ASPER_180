package com.novelic.novelictestapp.radar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable


// To enforce type safety
@Serializable
object Radar

// Single entry point to the radar feature
// Bridge between navigation and screen level composable and navigation
fun NavGraphBuilder.radarDestination(navController: NavController) {
    // defines composable destination to your navigation graph
    composable<Radar> {
        RadarScreen()
    }
}