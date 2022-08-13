package com.danilp.aquariumhelper.domain.use_case.calculation.fresh_water

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton
import kotlin.math.pow

@Singleton
class CalculateFreshCO2 {
    fun execute(kH: Double, pH: Double): CalculationResult =
        CalculationResult(result = .0 * kH * (10.0).pow(7.0 - pH))
}