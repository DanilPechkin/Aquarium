package com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
    Notes(NotesDestination, Icons.Default.Notes, R.string.notes_icon),
    Calculators(CalculatorsListDestination, Icons.Default.Calculate, R.string.calculators_icon),
    Main(MainAquariumScreenDestination, Icons.Default.Water, R.string.main_aquarium_icon),
    Dwellers(DwellersListDestination, Icons.Default.PanoramaFishEye, R.string.dwellers_icon),
    Plants(PlantsListDestination, Icons.Default.Biotech, R.string.plants_icon)
}