package com.danilp.aquariumhelper.domain.use_case.calculation.capacity

import com.danilp.aquariumhelper.domain.use_case.calculation.CalculationResult
import javax.inject.Singleton

@Singleton
class CalculateLitersCubicInches {
    /**
     * @param liters to calculate inches
     * @param inches to calculate liters
     */
    fun execute(liters: Double = 0.0, inches: Double = 0.0): CalculationResult =
        if (inches == 0.0)
            CalculationResult(result = liters * 61.0237)
        else
            CalculationResult(result = inches / 61.0237)
}