package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.edit

import com.danilp.aquariumhelper.domain.dweller.model.Dweller

data class DwellerEditState(
    val dweller: Dweller = Dweller.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val tempMeasure: String = "",
    val alkalinityMeasure: String = "",
    val capacityMeasure: String = "",
    //Stats
    val name: String = "",
    val genus: String = "",
    val amount: String = "",
    val minTemperature: String = "",
    val maxTemperature: String = "",
    val liters: String = "",
    val minPh: String = "",
    val maxPh: String = "",
    val minGh: String = "",
    val maxGh: String = "",
    val minKh: String = "",
    val maxKh: String = "",
    val description: String = "",
    // Stats errors
    val nameErrorCode: Int? = null,
    /*TODO: val genusError: Int? = null,*/
    val amountErrorCode: Int? = null,
    val minTemperatureErrorCode: Int? = null,
    val maxTemperatureErrorCode: Int? = null,
    val litersErrorCode: Int? = null,
    val minPhErrorCode: Int? = null,
    val maxPhErrorCode: Int? = null,
    val minGhErrorCode: Int? = null,
    val maxGhErrorCode: Int? = null,
    val minKhErrorCode: Int? = null,
    val maxKhErrorCode: Int? = null
)
