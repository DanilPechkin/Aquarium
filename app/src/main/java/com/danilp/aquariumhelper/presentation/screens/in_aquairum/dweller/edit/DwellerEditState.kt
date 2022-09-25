package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.edit

import com.danilp.aquariumhelper.domain.dweller.model.Dweller
import com.danilp.aquariumhelper.domain.use_case.validation.ValidationError

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
    val nameErrorCode: ValidationError? = null,
    /*TODO: val genusError: ValidationError? = null,*/
    val amountError: ValidationError? = null,
    val minTemperatureError: ValidationError? = null,
    val maxTemperatureError: ValidationError? = null,
    val litersError: ValidationError? = null,
    val minPhError: ValidationError? = null,
    val maxPhError: ValidationError? = null,
    val minGhError: ValidationError? = null,
    val maxGhError: ValidationError? = null,
    val minKhError: ValidationError? = null,
    val maxKhError: ValidationError? = null
)
