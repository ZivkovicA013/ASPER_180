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
import com.novelic.novelictestapp.R

@Composable
fun RadarScreen() {
    Column(
        modifier = Modifier
            .padding(start = 16.dp, top = 32.dp, bottom = 32.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Box(modifier = Modifier.weight(1f))
        {
            RadarGrid()
        }
        Spacer(modifier = Modifier.height(24.dp))
        RadarFooter()
    }
}


@Composable
fun RadarGrid() {

    val textMeasure = rememberTextMeasurer()
    val lineColor = MaterialTheme.colorScheme.secondary
    val gridLabelColor = MaterialTheme.colorScheme.primary

    Canvas(modifier = Modifier.fillMaxSize()) {
        // sensor real world ranges
        val xRange: IntRange = -10..10 // -10m -> 10m
        val yRange: IntRange = 0..10 // 0m -> 10m


        val gridWidth = size.width - 50f
        val gridHeight = size.height - 50f

        val xScale = gridWidth / (xRange.last - xRange.first)
        val yScale = gridHeight / (yRange.last - yRange.first)

        // draw vertical grid lines
        for (i in xRange step 2) {
            val x = (i - xRange.first) * xScale
            drawLine(
                start = Offset(x, 0f),
                end = Offset(x, gridHeight),
                strokeWidth = if (i == 0) 10f else 3f,
                color = lineColor
            )

            drawText(
                textMeasurer = textMeasure,
                "${i}m",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = gridLabelColor
                ),
                topLeft = Offset(x - 20f, gridHeight + 20f)
            )
        }

        // draw horizontal grid lines
        for (i in yRange step 2) {
            val y = gridHeight - (i - yRange.first) * yScale
            drawLine(
                start = Offset(0f, y),
                end = Offset(gridWidth, y),
                strokeWidth = if (i == 0) 10f else 3f,
                color = lineColor
            )

            if (i != 0) {
                drawText(
                    textMeasurer = textMeasure,
                    "${i}m",
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
fun RadarFooter() {
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
