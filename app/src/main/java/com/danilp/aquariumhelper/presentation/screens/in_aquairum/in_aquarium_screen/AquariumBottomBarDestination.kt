package com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.destinations.*
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

//TODO: add nice icons

enum class AquariumBottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Notes(NotesDestination, Icons.Rounded.FormatListBulleted, R.string.notes_icon),
    Calculators(CalculatorsListDestination, Icons.Rounded.Calculate, R.string.calculators_icon),
    Main(MainAquariumScreenDestination, Icons.Rounded.Water, R.string.main_aquarium_icon),
    Dwellers(DwellersListDestination, Icons.Rounded.SetMeal, R.string.dwellers_icon),
    Plants(PlantsListDestination, Icons.Rounded.Spa, R.string.plants_icon)
}