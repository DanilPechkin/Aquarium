package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.AquariumTopBar
import com.danilp.aquariumhelper.presentation.screens.OutlinedDropDownMenuField
import com.danilp.aquariumhelper.presentation.screens.destinations.AccountScreenDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    viewModel: SettingsViewModel = hiltViewModel()
) {

    val state = viewModel.state

    var isTopMenuExpanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AquariumTopBar(
                title = stringResource(R.string.settings_title),
                switchMenuVisibility = { isTopMenuExpanded = !isTopMenuExpanded },
                isMenuExpanded = isTopMenuExpanded,
                hideMenu = { isTopMenuExpanded = false },
                navigateBack = { navigator.navigateUp() },
                navigateToSettings = { navigator.navigate(SettingsScreenDestination) },
                navigateToAccount = { navigator.navigate(AccountScreenDestination()) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {

            OutlinedDropDownMenuField(
                label = stringResource(R.string.capacity_label),
                items = state.capacityList,
                selectedItem = state.capacityMeasure,
                changeSelectedItem = { viewModel.onEvent(SettingsEvent.CapacityChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedDropDownMenuField(
                label = stringResource(R.string.alkalinity_label),
                items = state.alkalinityList,
                selectedItem = state.alkalinityMeasure,
                changeSelectedItem = { viewModel.onEvent(SettingsEvent.AlkalinityChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedDropDownMenuField(
                label = stringResource(R.string.metric_label),
                items = state.metricList,
                selectedItem = state.metricMeasure,
                changeSelectedItem = { viewModel.onEvent(SettingsEvent.MetricChanged(it)) }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedDropDownMenuField(
                label = stringResource(R.string.temperature_label),
                items = state.temperatureList,
                selectedItem = state.temperatureMeasure,
                changeSelectedItem = { viewModel.onEvent(SettingsEvent.TemperatureChanged(it)) }
            )

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(SettingsEvent.DefaultButtonPressed)
                    }
                ) {
                    Text(text = stringResource(R.string.set_default_button))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(SettingsEvent.SaveButtonPressed)
                    }
                ) {
                    Text(text = stringResource(R.string.save_button))
                }
            }
        }
    }
}
