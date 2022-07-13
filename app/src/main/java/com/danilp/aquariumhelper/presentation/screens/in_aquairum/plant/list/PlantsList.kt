package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.presentation.navigation.nav_graphs.InAquariumNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@InAquariumNavGraph
@Destination
@Composable
fun PlantsList(
    navigator: DestinationsNavigator,
    viewModel: PlantsListViewModel = hiltViewModel()
) {
    val state = viewModel.state

    Text(text = state.aquariumId.toString())
}