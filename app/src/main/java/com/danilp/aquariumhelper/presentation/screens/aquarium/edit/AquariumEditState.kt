package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium

data class AquariumEditState(
    val aquarium: Aquarium = Aquarium.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val nameError: String? = null,
    val litersError: String? = null,
    val name: String = "",
    val liters: String = "",
    val description: String = ""
)
