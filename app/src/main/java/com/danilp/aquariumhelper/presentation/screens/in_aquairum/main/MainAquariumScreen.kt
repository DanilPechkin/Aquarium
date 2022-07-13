package com.danilp.aquariumhelper.presentation.screens.in_aquairum.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.danilp.aquariumhelper.presentation.navigation.nav_graphs.InAquariumNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@InAquariumNavGraph(start = true)
@Destination
@Composable
fun MainAquariumScreen(
    navigator: DestinationsNavigator
) {
    Text(text = "Main")
}