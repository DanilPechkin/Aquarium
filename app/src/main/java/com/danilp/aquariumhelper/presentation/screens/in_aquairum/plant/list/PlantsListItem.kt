package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danilp.aquariumhelper.domain.plant.model.Plant
import com.danilp.aquariumhelper.presentation.screens.GridItem

@Composable
fun PlantsListItem(
    plant: Plant,
    modifier: Modifier = Modifier
) {
    GridItem(
        name = plant.name,
        //TODO: make message
        message = "Здоровый",
        imageUri = plant.imageUri,
        cardColors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}