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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.novelic.novelictestapp.R

@Composable
fun RadarScreen() {
    Column(
        modifier = Modifier
            .padding(16.dp)
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
    Canvas(modifier = Modifier.fillMaxSize()) {
        val canvasHeight = size.height
        val canvasWidth = size.width
        val gridSpacing = 100f // 100px
        val stroke = 7f
        val lineColor = Color.Red

        println("Canvas height : $canvasHeight, Canvas width : $canvasWidth")

        // draw x-axis
        drawLine(
            start = Offset(0f, canvasHeight),
            end = Offset(canvasWidth, canvasHeight),
            strokeWidth = stroke * 2,
            color = lineColor
        )

        // draw y-axis
        drawLine(
            start = Offset(canvasWidth / 2, 0f),
            end = Offset(canvasWidth / 2, canvasHeight),
            strokeWidth = stroke * 2,
            color = lineColor
        )
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
                style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.ExtraBold)
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
                    style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.ExtraBold)
                )
                Text(
                    text = stringResource(R.string.short_range_radar_sensor),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
