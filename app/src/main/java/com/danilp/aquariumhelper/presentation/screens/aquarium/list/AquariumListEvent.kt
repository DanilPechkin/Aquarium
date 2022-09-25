package com.danilp.aquariumhelper.presentation.screens.aquarium.list

sealed interface AquariumListEvent {
    object Refresh : AquariumListEvent
    data class OnSearchQueryChange(val query: String) : AquariumListEvent
    data class OnAquariumClicked(val aquariumId: Int) : AquariumListEvent
}
