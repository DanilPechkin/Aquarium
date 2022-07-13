package com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun InAquariumScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            AquariumBottomBar(navController = navController)
        }
    ) { paddingValues ->
        DestinationsNavHost(
            navController = navController,
            navGraph = NavGraphs.inAquarium,
            modifier = Modifier.padding(paddingValues)
        )
    }
}