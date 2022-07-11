package com.danilp.aquariumhelper.presentation.screens.aquarium.list

import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium

data class AquariumListState(
    val aquariums: List<Aquarium> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null
)
