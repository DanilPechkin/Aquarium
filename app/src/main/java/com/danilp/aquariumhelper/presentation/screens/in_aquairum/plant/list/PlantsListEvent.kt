package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.list

sealed class PlantsListEvent {
    object Refresh: PlantsListEvent()
    data class OnSearchQueryChange(val query: String): PlantsListEvent()
}
