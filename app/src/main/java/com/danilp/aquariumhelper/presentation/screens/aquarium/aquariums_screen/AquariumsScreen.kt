package com.danilp.aquariumhelper.presentation.screens.aquarium.aquariums_screen

import androidx.compose.runtime.Composable
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun AquariumsScreen() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}