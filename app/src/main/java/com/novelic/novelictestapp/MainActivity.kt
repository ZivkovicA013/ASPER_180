package com.novelic.novelictestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.novelic.novelictestapp.dashboard.Dashboard
import com.novelic.novelictestapp.dashboard.dashboardDestination
import com.novelic.novelictestapp.radar.radarDestination
import com.novelic.novelictestapp.ui.theme.NovelicTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Content()
        }
    }
}


@Composable
fun Content() {
    NovelicTestAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            // built high in hierarchy
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = Dashboard
            ) {
                dashboardDestination(navController)
                radarDestination(navController)
            }

        }
    }
}