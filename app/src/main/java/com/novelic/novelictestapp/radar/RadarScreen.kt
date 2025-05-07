package com.novelic.novelictestapp.radar

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.novelic.novelictestapp.R

@Composable
fun RadarScreen(navController: NavController, gridSize: Int) {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 32.dp, bottom = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(modifier = Modifier.weight(1f))
        {
            RadarGrid(gridSize = gridSize.toFloat())
        }
        Spacer(modifier = Modifier.height(24.dp))
        RadarFooter()
    }
}


@Composable
fun RadarGrid(modifier: Modifier = Modifier, gridSize: Float) {

    val textMeasure = rememberTextMeasurer()
    val lineColor = MaterialTheme.colorScheme.secondary
    val gridLabelColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.fillMaxSize()) {

        // calculates interval spacing for the radar gird
        val gridInterval = calculateGridInterval(gridSize)

        // calculates grid points based on the grid size and grid interval
        val xGridPoints = generateGridPoints(-gridSize, gridSize, gridInterval)
        val yGridPoints = generateGridPoints(0f, gridSize, gridInterval)

        val gridWidth = size.width - 50f
        val gridHeight = size.height - 50f

        val xScale = gridWidth / (xGridPoints.last() - xGridPoints.first())
        val yScale = gridHeight / (yGridPoints.last() - yGridPoints.first())

        // draw vertical grid lines
        xGridPoints.forEach { xPoint ->
            val x = (xPoint - xGridPoints.first()) * xScale
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, gridHeight),
                strokeWidth = if (xPoint == 0f) 10f else 3f,
                color = lineColor
            )

            // draw labels on the x grid axis
            drawText(
                textMeasurer = textMeasure,
                "${xPoint}m",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = gridLabelColor
                ),
                topLeft = Offset(x - 20f, gridHeight + 20f)
            )
        }

        // draw horizontal grid lines
        yGridPoints.forEach { yPoint ->
            val y = gridHeight - (yPoint - yGridPoints.first()) * yScale
            drawLine(
                start = Offset(0f, y),
                end = Offset(gridWidth, y),
                strokeWidth = if (yPoint == 0f) 10f else 3f,
                color = lineColor
            )

            if (yPoint != yGridPoints.first()) {
                // draw labels on the y grid axis
                drawText(
                    textMeasurer = textMeasure,
                    "${yPoint}m",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = gridLabelColor
                    ),
                    topLeft = Offset(gridWidth / 2 + 10f, y),
                )
            }
        }
    }
}


@Composable
fun RadarFooter(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.novelic),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.car_black),
                contentDescription = null,
            )
        }
        Spacer(Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Column {
                Text(
                    text = stringResource(R.string.asper_180),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
                Text(
                    text = stringResource(R.string.short_range_radar_sensor),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                )
            }
        }
    }
}

private fun calculateGridInterval(gridSize: Float): Float = when (gridSize) {
    2f -> 0.5f
    10f -> 1f
    100f -> 10f
    else -> 1f
}

private fun generateGridPoints(start: Float, end: Float, step: Float): List<Float> {
    return generateSequence(start) { it + step }
        .takeWhile { it <= end }
        .toList()
}
