package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.list

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danilp.aquariumhelper.domain.dweller.model.Dweller
import com.danilp.aquariumhelper.presentation.screens.GridItem

@Composable
fun DwellersListItem(
    dweller: Dweller,
    modifier: Modifier = Modifier
) {
    GridItem(
        name = dweller.name,
        message = "Здоровый",
        cardColors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}