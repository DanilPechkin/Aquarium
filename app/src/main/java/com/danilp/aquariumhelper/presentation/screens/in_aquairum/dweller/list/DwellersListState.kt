package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.list

import com.danilp.aquariumhelper.domain.dweller.model.Dweller

data class DwellersListState(
    val dwellers: List<Dweller> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = "",
    val error: String? = null,
    val aquariumId: Int = 0
)
