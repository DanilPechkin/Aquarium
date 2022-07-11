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
import com.danilp.aquariumhelper.presentation.screens.startAppDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.navigate

@Composable
fun AquariumBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val currentDestination: Destination? = (navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination) as Destination?

    BottomAppBar {
        AquariumBottomBarDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = currentDestination == (destination.direction as Destination?),
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
