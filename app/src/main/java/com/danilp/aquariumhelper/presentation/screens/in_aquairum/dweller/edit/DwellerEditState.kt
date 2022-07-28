package com.danilp.aquariumhelper.presentation.screens.in_aquairum.dweller.edit

import com.danilp.aquariumhelper.domain.dweller.model.Dweller

data class DwellerEditState(
    val dweller: Dweller = Dweller.createEmpty(),
    val isLoading: Boolean = false,
    val error: String? = null,
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
    val nameError: String? = null,
    /*TODO: val genusError: String? = null,*/
    val amountError: String? = null,
    val minTemperatureError: String? = null,
    val maxTemperatureError: String? = null,
    val litersError: String? = null,
    val minPhError: String? = null,
    val maxPhError: String? = null,
    val minGhError: String? = null,
    val maxGhError: String? = null,
    val minKhError: String? = null,
    val maxKhError: String? = null
)
