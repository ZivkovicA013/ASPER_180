package com.novelic.novelictestapp.radar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


// To enforce type safety
@Serializable
data class Radar(val gridSize: Int)

// Single entry point to the radar feature
// Bridge between navigation and screen level composable and navigation
fun NavGraphBuilder.radarDestination(navController: NavController) {
    // defines composable destination to your navigation graph
    composable<Radar> { navBackStackEntry ->
        val radar: Radar = navBackStackEntry.toRoute()
        RadarScreen(navController, radar.gridSize)
    }
}