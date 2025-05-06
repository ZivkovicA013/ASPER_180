package com.novelic.novelictestapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun NovelicTestAppTheme(
    content: @Composable () -> Unit
) {

    val colorScheme = darkColorScheme(
        primary = Color.White,
        secondary = Color.Red,
        tertiary = Blue,
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}