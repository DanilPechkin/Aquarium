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
    val nameErrorCode: Int? = null,
    /*TODO: val genusError: Int? = null,*/
    val minTemperatureErrorCode: Int? = null,
    val maxTemperatureErrorCode: Int? = null,
    val minPhErrorCode: Int? = null,
    val maxPhErrorCode: Int? = null,
    val minGhErrorCode: Int? = null,
    val maxGhErrorCode: Int? = null,
    val minKhErrorCode: Int? = null,
    val maxKhErrorCode: Int? = null,
    val minCO2ErrorCode: Int? = null,
    val minIlluminationErrorCode: Int? = null
)
