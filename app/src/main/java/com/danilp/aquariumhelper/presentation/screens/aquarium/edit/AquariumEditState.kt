package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

import com.danilp.aquariumhelper.domain.aquairum.model.Aquarium
import com.danilp.aquariumhelper.domain.use_case.validation.ValidationError

data class AquariumEditState(
    val aquarium: Aquarium = Aquarium.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val capacityMeasure: String = "",
    val nameError: ValidationError? = null,
    val litersError: ValidationError? = null,
    val name: String = "",
    val liters: String = "",
    val description: String = ""
)
