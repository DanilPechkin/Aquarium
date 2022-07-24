package com.danilp.aquariumhelper.presentation.screens.in_aquairum.plant.edit

import com.danilp.aquariumhelper.domain.plant.model.Plant

data class PlantEditState(
    val plant: Plant = Plant.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
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
    val nameError: String? = null,
    /*TODO: val genusError: String? = null,*/
    val minTemperatureError: String? = null,
    val maxTemperatureError: String? = null,
    val minPhError: String? = null,
    val maxPhError: String? = null,
    val minGhError: String? = null,
    val maxGhError: String? = null,
    val minKhError: String? = null,
    val maxKhError: String? = null,
    val minCO2Error: String? = null,
    val minIlluminationError: String? = null
)
