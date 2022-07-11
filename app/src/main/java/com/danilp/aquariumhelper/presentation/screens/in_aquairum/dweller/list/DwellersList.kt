package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DwellersList(
    navigator: DestinationsNavigator
) {
    Text(text = "Dwellers")
}