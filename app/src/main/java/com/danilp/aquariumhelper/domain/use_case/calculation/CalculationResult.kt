package com.danilp.aquariumhelper.domain.use_case.calculation

data class CalculationResult(
    val result: Double = 0.0,
    val successful: Boolean = true,
    val errorMessage: String? = null
)
