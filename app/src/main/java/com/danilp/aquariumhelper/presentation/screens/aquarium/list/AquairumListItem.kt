package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.presentation.screens.GridItem

@Composable
fun AquariumListItem(
    aquarium: Aquarium,
    modifier: Modifier = Modifier
) {
    GridItem(
        name = aquarium.name,
        //TODO: make message
        message = "2 Уведомления",
        imageUri = aquarium.imageUri,
        cardColors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier
    )
}