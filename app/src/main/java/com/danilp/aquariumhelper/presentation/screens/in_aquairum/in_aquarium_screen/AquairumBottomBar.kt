package com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.danilp.aquariumhelper.presentation.screens.appCurrentDestinationAsState
import com.danilp.aquariumhelper.presentation.screens.destinations.Destination
import com.danilp.aquariumhelper.presentation.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.navigate

//TODO make selected work

@Composable
fun AquariumBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?:  NavGraphs.inAquarium.startAppDestination


    BottomAppBar {
        AquariumBottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = destination.direction == currentDestination,
                onClick = {
                    navController.navigate(destination.direction) { launchSingleTop = true }
                },
                icon = {
                    Icon(
                        imageVector = destination.icon,
                        contentDescription = stringResource(destination.label)
                    )
                }
            )
        }
    }
}
