package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class CalculateLitersCubicFeet {
    /**
     * @param liters to calculate feet
     * @param feet to calculate liters
     */
    fun execute(liters: Double = 0.0, feet: Double = 0.0): CalculationResult =
        if (feet == 0.0)
            CalculationResult(result = liters * 0.0353147)
        else
            CalculationResult(result = feet / 0.0353147)
}