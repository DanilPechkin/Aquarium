package com.danilp.aquariumhelper.presentation.screens.in_aquairum.notes

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun Notes(
    navigator: DestinationsNavigator
) {
    Text(text = "Notes")
}