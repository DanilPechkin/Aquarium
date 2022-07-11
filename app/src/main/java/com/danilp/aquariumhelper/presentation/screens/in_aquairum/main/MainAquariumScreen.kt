package com.danilp.aquariumhelper.presentation.screens.in_aquairum.main

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun MainAquariumScreen(
    navigator: DestinationsNavigator
) {
    Text(text = "Main")
}