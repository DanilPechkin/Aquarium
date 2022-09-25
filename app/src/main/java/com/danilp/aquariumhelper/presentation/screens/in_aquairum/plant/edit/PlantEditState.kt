package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.edit

import com.danilp.aquariumhelper.domain.plant.model.Plant
import com.danilp.aquariumhelper.domain.use_case.validation.ValidationError

data class PlantEditState(
    val plant: Plant = Plant.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val tempMeasure: String = "",
    val alkalinityMeasure: String = "",
    // Stats
    val name: String = "",
    val genus: String = "",
    val minTemperature: String = "",
    val maxTemperature: String = "",
    val minPh: String = "",
    val maxPh: String = "",
    val minGh: String = "",
    val maxGh: String = "",
    val minKh: String = "",
    val maxKh: String = "",
    val minCO2: String = "",
    val minIllumination: String = "",
    val description: String = "",
    // Stats errors
    val nameErrorCode: ValidationError? = null,
    /*TODO: val genusError: ValidationError? = null,*/
    val minTemperatureError: ValidationError? = null,
    val maxTemperatureError: ValidationError? = null,
    val minPhError: ValidationError? = null,
    val maxPhError: ValidationError? = null,
    val minGhError: ValidationError? = null,
    val maxGhError: ValidationError? = null,
    val minKhError: ValidationError? = null,
    val maxKhError: ValidationError? = null,
    val minCO2Error: ValidationError? = null,
    val minIlluminationError: ValidationError? = null
)
