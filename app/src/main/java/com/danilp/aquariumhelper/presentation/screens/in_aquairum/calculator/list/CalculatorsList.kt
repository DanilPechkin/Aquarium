package com.danilp.aquariumhelper.presentation.screens.in_aquairum.calculator.list

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun CalculatorsList(
    navigator: DestinationsNavigator
) {
    Text(text = "Calculators list")
}