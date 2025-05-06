package com.novelic.novelictestapp.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.novelic.novelictestapp.R
import com.novelic.novelictestapp.radar.Radar


@Composable
fun DashboardScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.dashborad_headline),
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Bold)
        )

        Spacer(Modifier.height(64.dp))

        Text(
            text = stringResource(R.string.grid_configuration),
            style = MaterialTheme.typography.displaySmall
        )

        val (selectedOption, onOptionSelected) = remember { mutableStateOf(GridConfigurationOptions[0]) }

        GridConfigurationSelection(
            selectedOption = selectedOption,
            onOptionSelected = onOptionSelected
        )

        Spacer(Modifier.height(32.dp))

        Button(onClick = {
            when (selectedOption) {
                "0m - 2m" -> navController.navigate(Radar(2))
                "0m - 10m" -> navController.navigate(Radar(10))
                "0m - 100m" -> navController.navigate(Radar(100))
            }

        }) {
            Text(
                stringResource(R.string.start),
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

val GridConfigurationOptions = listOf("0m - 2m", "0m - 10m", "0m - 100m")

@Composable
fun GridConfigurationSelection(
    modifier: Modifier = Modifier,
    selectedOption: String,
    onOptionSelected: (String) -> Unit
) {


    Column(modifier = Modifier.selectableGroup()) {
        GridConfigurationOptions.forEach { text ->
            Row(
                Modifier
                    .height(56.dp)
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}