package com.danilp.aquariumhelper.presentation.screens.aquarium.list

sealed class AquariumListEvent {
    object Refresh: AquariumListEvent()
    data class OnSearchQueryChange(val query: String): AquariumListEvent()
}
