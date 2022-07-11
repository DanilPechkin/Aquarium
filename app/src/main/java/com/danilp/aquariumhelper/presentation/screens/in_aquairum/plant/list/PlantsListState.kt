package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

import com.danilp.aquariumhelper.domain.plant.model.Plant

data class PlantsListState(
    val plants: List<Plant> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null,
    val aquariumId: Int = 0
)
